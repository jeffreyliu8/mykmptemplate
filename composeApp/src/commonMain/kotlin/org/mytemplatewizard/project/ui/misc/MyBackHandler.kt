package org.mytemplatewizard.project.ui.misc

import androidx.compose.runtime.Composable

/**
 * An effect for handling the back event.
 *
 * Calling this in your composable adds the given lambda to the BackGestureDispatcher.
 *
 * If this is called by nested composables, if enabled, the inner most composable will consume the
 * call to system back and invoke its lambda. The call will continue to propagate up until it finds
 * an enabled BackHandler.
 *
 * @param enabled if this BackHandler should be enabled
 * @param onBack the action invoked by system back event
 */
@Composable
expect fun BackHandler(enabled: Boolean = true, onBack: () -> Unit)