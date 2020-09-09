package pl.training.shop.rx;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.training.shop.rx.github.GithubService;
import pl.training.shop.rx.wikipedia.WikipediaService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Application {

    private final GithubService githubService = new GithubService(retrofitBuilder("https://api.github.com/"));
    private final WikipediaService wikipediaService = new WikipediaService(retrofitBuilder("https://en.wikipedia.org/w/"));
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Retrofit retrofitBuilder(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                        .build())
                .build();
    }

}
