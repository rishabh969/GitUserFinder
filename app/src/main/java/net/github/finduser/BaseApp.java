package net.github.finduser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.github.finduser.deps.DaggerDeps;
import net.github.finduser.deps.Deps;
import net.github.finduser.networking.NetworkModule;

import java.io.File;

/**
 * Created by Rishabh Gupta.
 */
public class BaseApp  extends AppCompatActivity{
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}
