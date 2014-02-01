package com.ninetwozero.bf4intel.network;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.github.kevinsawicki.http.HttpRequest;
import com.ninetwozero.bf4intel.utils.Result;
import com.ninetwozero.bf4intel.utils.exception.Failure;

public class IntelLoader extends AsyncTaskLoader<Result> {

    private final BaseSimpleRequest request;

    public IntelLoader(Context context, BaseSimpleRequest request) {
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
            return actOnFailure(failure);
        }
    }

    private Result actOn(final String theResult) {
        Result result = Result.SUCCESS;
        result.setResultMessage(theResult);
        return result;
    }

    private Result actOnFailure(final Failure failure) {
        if (failure.getCause() instanceof HttpRequest.HttpRequestException) {
            return Result.NETWORK_FAILURE;
        } else if (failure.getMessage().equals("")) {
            return Result.FAILURE;
        }
        return generateErrorResult(failure.getMessage());
    }

    private Result generateErrorResult(final String message) {
        final Result result = Result.ERROR;
        result.setResultMessage(message);
        return result;
    }
}
