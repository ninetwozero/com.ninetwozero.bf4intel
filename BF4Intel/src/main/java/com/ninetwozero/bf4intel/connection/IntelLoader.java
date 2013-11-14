package com.ninetwozero.bf4intel.connection;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.github.kevinsawicki.http.HttpRequest;
import com.ninetwozero.bf4intel.utils.Result;
import com.ninetwozero.bf4intel.utils.exception.Failure;

public class IntelLoader extends AsyncTaskLoader<Result> {

    private final ConnectionRequest request;

    public IntelLoader(Context context, ConnectionRequest request) {
        super(context);
        this.request = request;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Result loadInBackground() {
        try {
            return actOn(request.execute());
        } catch (Failure failure) {
            if (failure.getCause() instanceof HttpRequest.HttpRequestException) {
                return Result.NETWORK_FAILURE;
            } else {
                return Result.FAILURE;
            }
        }
    }

    private Result actOn(String loginResult) {
        Result result = Result.SUCCESS;
        result.setResultMessage(loginResult);
        return result;
    }
}
