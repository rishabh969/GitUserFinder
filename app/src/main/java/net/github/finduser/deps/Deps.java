package net.github.finduser.deps;


import net.github.finduser.networking.NetworkModule;
import net.github.finduser.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Rishabh.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(SearchActivity searchActivity);
}
