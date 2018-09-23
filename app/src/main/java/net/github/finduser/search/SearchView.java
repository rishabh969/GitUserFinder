package net.github.finduser.search;

import net.github.finduser.models.SearchResponse;

public interface SearchView {
    void showWait();

    void removeWait();

    void onFailure(String errorMsg);

    void getUserListSuccess(SearchResponse response);
}
