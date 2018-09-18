package com.btandjaja.www.bakingrecipes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.btandjaja.www.bakingrecipes.data.AppExecutors;
import com.btandjaja.www.bakingrecipes.data.RecipeDatabase;
import com.btandjaja.www.bakingrecipes.data.RecipeEntry;
import com.btandjaja.www.bakingrecipes.data.RecipeListViewModel;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class BakingWidgetProvider extends AppWidgetProvider {

//    private static RecipeDatabase mDb;

    private static final int DEFAULT_POSITION = 0;
    private static final String DEFAULT_RECIPE = "nutella_pie";
    private static RecipeDatabase mDb;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        mDb = RecipeDatabase.getsInstance(context);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        appWidgetManager.updateAppWidget(appWidgetId, rv);
    }

    private static RemoteViews getSingleRecipeView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_widget);
        Intent intent = new Intent(context, ListOfRecipesActivity.class);
        Bundle extras = new Bundle();
        extras.putInt(context.getString(R.string.POSITION), DEFAULT_POSITION);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        views.setImageViewResource(R.id.iv_widget_baking_image,
                context.getResources().getIdentifier(DEFAULT_RECIPE, "drawable", context.getPackageName()));
        views.setOnClickPendingIntent(R.id.iv_widget_baking_image, pendingIntent);
        return views;
    }

    private static class GetDatabase extends AsyncTask<mDb, Void, Void> {
        RecipeDatabase mDb;
        public GetDatabase(RecipeDatabase db) { this.mDb = db; }


        @Override
        protected Void doInBackground(mDb... mDbs) {
            return null;
        }
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

