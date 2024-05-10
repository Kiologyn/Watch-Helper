/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.kiologyn.watchhelper.activity

import androidx.compose.runtime.Composable
import com.kiologyn.watchhelper.component.WatchHelperActivity
import com.kiologyn.watchhelper.ui.Navigation

class MainActivity : WatchHelperActivity() {
    @Composable
    override fun Layout() = Navigation()
}
