package pl.training.shop.rx;

import io.reactivex.Observable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ObservableReader {

    public static Observable<String> from(InputStream inputStream) {
        return Observable.create(emitter -> {
            try (InputStreamReader input = new InputStreamReader(inputStream);
                 BufferedReader reader = new BufferedReader(input)) {
                String text;
                while ((text = reader.readLine()) != null) {
                    emitter.onNext(text);
                }
                emitter.onComplete();
            }
        });
    }

}
