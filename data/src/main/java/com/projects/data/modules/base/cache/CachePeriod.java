package com.projects.data.modules.base.cache;

import android.text.format.DateUtils;
import androidx.annotation.LongDef;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@LongDef({CachePeriod.EXPIRE_FAST, CachePeriod.EXPIRE_NORMAL, CachePeriod.EXPIRE_LONG, CachePeriod.EXPIRE_INFINITY})
public @interface CachePeriod {
    long EXPIRE_FAST = DateUtils.MINUTE_IN_MILLIS;
    long EXPIRE_NORMAL = 10 * DateUtils.MINUTE_IN_MILLIS;
    long EXPIRE_LONG = DateUtils.HOUR_IN_MILLIS;
    long EXPIRE_INFINITY = 10 * DateUtils.YEAR_IN_MILLIS;
}
