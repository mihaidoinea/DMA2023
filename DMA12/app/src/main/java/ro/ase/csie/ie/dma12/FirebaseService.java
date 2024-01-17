package ro.ase.csie.ie.dma12;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {

    private static final String MOVIE_TABLE = "movies";
    private static FirebaseService firebaseService;
    private DatabaseReference databaseReference;

    public FirebaseService()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference(MOVIE_TABLE);
    }

    public static FirebaseService getInstance()
    {
        if(firebaseService == null)
        {
            synchronized (FirebaseService.class)
            {
                if(firebaseService == null)
                {
                    firebaseService = new FirebaseService();
                }
            }
        }
        return  firebaseService;
    }

    public void attachDataChangeEventListener(final Callback<List<Movie>> callback)
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Movie> movies = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren())
                {
                    Movie movie = data.getValue(Movie.class);
                    if(movie != null)
                        movies.add(movie);
                }
                callback.runResultOnUiThread(movies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void upsert(Movie movie) {
        if(movie == null)
            return;
        if(movie.getMovieId() == null)
        {
            String idKey = databaseReference.push().getKey();
            movie.setMovieId(idKey);
        }
        databaseReference.child(movie.getMovieId().toString()).setValue(movie);

    }
}
