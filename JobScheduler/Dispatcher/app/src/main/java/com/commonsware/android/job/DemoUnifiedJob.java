/***
 Copyright (c) 2016 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 From _The Busy Coder's Guide to Android Development_
 https://commonsware.com/Android
 */

package com.commonsware.android.job;

import android.support.annotation.NonNull;
import android.util.Log;
import com.evernote.android.job.Job;

public class DemoUnifiedJob extends Job {
  public static final String JOB_TAG=
    DemoUnifiedJob.class.getCanonicalName();

  @NonNull
  @Override
  protected Result onRunJob(Params params) {
    Log.d(getClass().getSimpleName(), "scheduled unified work begins");

    if (getParams()
          .getExtras()
          .getBoolean(PollReceiver.EXTRA_IS_DOWNLOAD, false)) {
      new DownloadJob().run();  // do synchronously, as we are on
                                // a background thread already
    }

    Log.d(getClass().getSimpleName(), "scheduled unified work ends");

    return(Result.SUCCESS);
  }
}
