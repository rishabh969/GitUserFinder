package net.github.finduser.networking;


import net.github.finduser.models.SearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Rishabh.
 */
public interface NetworkService {
    @GET("users?")
    Observable<SearchResponse> getUserList(@Query("q") String user);
}
