package com.example.appwidgetsexamples.widgets

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import com.example.appwidgetsexamples.MainActivity
import com.example.appwidgetsexamples.R

class ExampleAppWidgets : AppWidgetProvider() {
    private val TAG = ExampleAppWidgets::class.qualifiedName

    @SuppressLint("RemoteViewLayout")
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach {
            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                /* context = */ context,
                /* requestCode = */  0,
                /* intent = */ Intent(context, MainActivity::class.java),
                /* flags = */ PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val views=RemoteViews(context?.packageName, R.layout.example_app_widget)
            views.setOnClickPendingIntent(R.id.widgetButton,pendingIntent)
            appWidgetManager?.updateAppWidget(it,views)
        }
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        Log.e(TAG, "onEnabled: " )
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
        Log.e(TAG, "onDeleted: ${appWidgetIds.contentToString()}" )
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Log.e(TAG, "onDisabled: ", )
    }

}