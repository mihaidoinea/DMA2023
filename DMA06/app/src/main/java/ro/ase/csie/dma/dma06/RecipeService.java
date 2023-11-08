package ro.ase.csie.dma.dma06;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface RecipeService {

    @GET("b/OCIE")
    Call<List<Recipe>> getRecipes();
}
