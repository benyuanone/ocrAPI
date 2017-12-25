package com.ourway.base.zk.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ourway.base.zk.component.BaseMessageboxDlg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.zkoss.mesg.Messages;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zul.mesg.MZul;

public class BaseMessagebox {
    private static final Logger log = LoggerFactory.getLogger(BaseMessagebox.class);
    private static String _templ = "~./zul/html/messagebox.zul";

    /** A symbol consisting of a question mark in a circle.
     * <p>Since 3.5.0, they are actually style class names to display the icon.
     */
    public static final String QUESTION = "z-messagebox-icon z-messagebox-question";
    /** A symbol consisting of an exclamation point in a triangle with
     * a yellow background
     * <p>Since 3.5.0, they are actually style class names to display the icon.
     */
    public static final String EXCLAMATION = "z-messagebox-icon z-messagebox-exclamation";
    /** A symbol of a lower case letter i in a circle.
     * <p>Since 3.5.0, they are actually style class names to display the icon.
     */
    public static final String INFORMATION = "z-messagebox-icon z-messagebox-information";
    /** A symbol consisting of a white X in a circle with a red background.
     * <p>Since 3.5.0, they are actually style class names to display the icon.
     */
    public static final String ERROR = "z-messagebox-icon z-messagebox-error";
    /** Contains no symbols. */
    public static final String NONE = null;

    /** A OK button. */
    public static final int OK = 0x0001;
    /** A Cancel button. */
    public static final int CANCEL = 0x0002;
    /** A Yes button. */
    public static final int YES = 0x0010;
    /** A No button. */
    public static final int NO = 0x0020;
    /** A Abort button. */
    public static final int ABORT = 0x0100;
    /** A Retry button. */
    public static final int RETRY = 0x0200;
    /** A IGNORE button. */
    public static final int IGNORE = 0x0400;

    /** The event to indicate the Yes button being clicked.
     * @since 5.0.8
     */
    public static final String ON_YES = "onYes";
    /** The event to indicate the No button being clicked.
     * @since 5.0.8
     */
    public static final String ON_NO = "onNo";
    /** The event to indicate the RETRY button being clicked.
     * @since 5.0.8
     */
    public static final String ON_RETRY = "onRetry";
    /** The event to indicate the Abort button being clicked.
     * @since 5.0.8
     */
    public static final String ON_ABORT = "onAbort";
    /** The event to indicate the Ignore button being clicked.
     * @since 5.0.8
     */
    public static final String ON_IGNORE = "onIgnore";
    /** The event to indicate the OK button being clicked.
     * @since 5.0.8
     */
    public static final String ON_OK = Events.ON_OK;
    /** The event to indicate the Cancel button being clicked.
     * @since 5.0.8
     */
    public static final String ON_CANCEL = Events.ON_CANCEL;

    /** Shows a message box and returns what button is pressed.
     *
     * @param title the title. If null, {@link WebApp#getAppName} is used.
     * @param buttons an array of buttons to show.
     * The buttons will be displayed in the same order in the array.
     * @param btnLabels the label used for each button specified in the buttons
     * argument. If null, the default label will be used.
     * @param icon one of predefined images: {@link #QUESTION},
     * {@link #EXCLAMATION}, {@link #ERROR}, {@link #NONE}, or any style class
     * name(s) to show an image.
     * @param focus one of button to have to focus. If null, the first button
     * will gain the focus.
     * @param listener the event listener which is invoked when a button
     * is clicked. Ignored if null.
     * It is useful if the event processing thread is disabled
     * ({@link org.zkoss.zk.ui.util.Configuration#enableEventThread}).
     * If the event processing thread is disabled (system default), this method always
     * return {@link BaseMessagebox.Button#OK}. To know which button is pressed, you have to pass an
     * event listener. Then, when the user clicks a button, the event
     * listener is invoked with an instance of {@link BaseMessagebox.ClickEvent}.
     * You can identify which button is clicked
     * by examining {@link BaseMessagebox.ClickEvent#getButton} or {@link BaseMessagebox.ClickEvent#getName}.
     * If the close button is clicked, the onClose event is sent and
     * {@link BaseMessagebox.ClickEvent#getButton} return null;
     * @return the button being pressed.
     * Note: if the event processing thread is disabled (system default), it always
     * returns {@link BaseMessagebox.Button#OK}.
     * @param params the parameters passed to the template. Ignored if null.
     * Notice it will override the default parameters if there is any conflict.
     * You could pass anything as long as the template ({@link #setTemplate})
     * recognized. For the default template, typical parameters are<br/>
     * <code>width</code>: the width of the dialog.<br/>
     * <code>icon</code>: the URI of the icon
     * <code>sclass</code>: the CSS class name of the top level Window (since 7.0.1)
     * @since 6.0.0
     */
    public static BaseMessagebox.Button show(String message, String title, BaseMessagebox.Button[] buttons, String[] btnLabels, String icon,
                                             BaseMessagebox.Button focus, EventListener<BaseMessagebox.ClickEvent> listener, Map<String, String> params) {
        final Map<String, Object> arg = new HashMap<String, Object>();
        arg.put("message", message);
        arg.put("title", title != null ? title : Executions.getCurrent().getDesktop().getWebApp().getAppName());
        arg.put("icon", icon);

        if (buttons == null)
            buttons = DEFAULT_BUTTONS;

        int btnmask = 0;
        for (int j = 0; j < buttons.length; ++j) {
            if (buttons[j] == null)
                throw new IllegalArgumentException("The " + j + "-th button is null");

            //Backward compatible to ZK 5: put buttons and id to arg
            btnmask += buttons[j].id;
            arg.put(buttons[j].stringId, buttons[j].id);
        }
        arg.put("buttons", btnmask);

        if (params != null)
            arg.putAll(params);

        final BaseMessageboxDlg dlg = (BaseMessageboxDlg) Executions.createComponents(_templ, null, arg);
        dlg.setEventListener(listener);
        dlg.setButtons(buttons, btnLabels);
        if (focus != null)
            dlg.setFocus(focus);

        if (dlg.getDesktop().getWebApp().getConfiguration().isEventThreadEnabled()) {
            try {
                dlg.doModal();
            } catch (Throwable ex) {
                try {
                    dlg.detach();
                } catch (Throwable ex2) {
                    log.warn("Failed to detach when recovering from an error", ex2);
                }
                throw UiException.Aide.wrap(ex);
            }
            return dlg.getResult();
        } else {
            dlg.doHighlighted();
            return BaseMessagebox.Button.OK;
        }
    }

    /** Shows a message box and returns what button is pressed.
     *
     * @param title the title. If null, {@link WebApp#getAppName} is used.
     * @param buttons an array of buttons to show.
     * The buttons will be displayed in the same order in the array.
     * @param btnLabels the label used for each button specified in the buttons
     * argument. If null, the default label will be used.
     * @param icon one of predefined images: {@link #QUESTION},
     * {@link #EXCLAMATION}, {@link #ERROR}, {@link #NONE}, or any style class
     * name(s) to show an image.
     * @param focus one of button to have to focus. If null, the first button
     * will gain the focus.
     * @param listener the event listener which is invoked when a button
     * is clicked. Ignored if null.
     * It is useful if the event processing thread is disabled
     * ({@link org.zkoss.zk.ui.util.Configuration#enableEventThread}).
     * If the event processing thread is disabled (system default), this method always
     * return {@link BaseMessagebox.Button#OK}. To know which button is pressed, you have to pass an
     * event listener. Then, when the user clicks a button, the event
     * listener is invoked with an instance of {@link BaseMessagebox.ClickEvent}.
     * You can identify which button is clicked
     * by examining {@link BaseMessagebox.ClickEvent#getButton} or {@link BaseMessagebox.ClickEvent#getName}.
     * If the close button is clicked, the onClose event is sent and
     * {@link BaseMessagebox.ClickEvent#getButton} return null;
     * @return the button being pressed.
     * Note: if the event processing thread is disabled (system default), it always
     * returns {@link BaseMessagebox.Button#OK}.
     * @since 6.0.0
     */
    public static BaseMessagebox.Button show(String message, String title, BaseMessagebox.Button[] buttons, String[] btnLabels, String icon,
                                             BaseMessagebox.Button focus, EventListener<BaseMessagebox.ClickEvent> listener) {
        return show(message, title, buttons, btnLabels, icon, focus, listener, null);
    }

    private static final BaseMessagebox.Button[] DEFAULT_BUTTONS = new BaseMessagebox.Button[] { BaseMessagebox.Button.OK };

    /** Shows a message box and returns what button is pressed.
     *
     * @param title the title. If null, {@link WebApp#getAppName} is used.
     * @param buttons an array of buttons to show.
     * The buttons will be displayed in the same order in the array.
     * @param icon one of predefined images: {@link #QUESTION},
     * {@link #EXCLAMATION}, {@link #ERROR}, {@link #NONE}, or any style class
     * name(s) to show an image.
     * @param focus one of button to have to focus. If null, the first button
     * will gain the focus.
     * @param listener the event listener which is invoked when a button
     * is clicked. Ignored if null.
     * It is useful if the event processing thread is disabled
     * ({@link org.zkoss.zk.ui.util.Configuration#enableEventThread}).
     * If the event processing thread is disabled (system default), this method always
     * return {@link BaseMessagebox.Button#OK}. To know which button is pressed, you have to pass an
     * event listener. Then, when the user clicks a button, the event
     * listener is invoked with an instance of {@link BaseMessagebox.ClickEvent}.
     * You can identify which button is clicked
     * by examining {@link BaseMessagebox.ClickEvent#getButton} or {@link BaseMessagebox.ClickEvent#getName}.
     * If the close button is clicked, the onClose event is sent and
     * {@link BaseMessagebox.ClickEvent#getButton} return null;
     * @return the button being pressed.
     * Note: if the event processing thread is disabled (system default), it always
     * returns {@link BaseMessagebox.Button#OK}.
     * @since 6.0.0
     */
    public static BaseMessagebox.Button show(String message, String title, BaseMessagebox.Button[] buttons, String icon, BaseMessagebox.Button focus,
                                             EventListener<BaseMessagebox.ClickEvent> listener) {
        return show(message, title, buttons, null, icon, focus, listener, null);
    }

    /** Shows a message box and returns what button is pressed.
     * A shortcut to show(message, title, buttons, icon, null, listener).
     * @since 6.0.0
     */
    public static BaseMessagebox.Button show(String message, String title, BaseMessagebox.Button[] buttons, String icon,
                                             EventListener<BaseMessagebox.ClickEvent> listener) {
        return show(message, title, buttons, null, icon, null, listener, null);
    }

    /** Shows a message box and returns what button is pressed.
     * A shortcut to show(message, null, buttons, INFORMATION, null, listener).
     * @since 6.0.0
     */
    public static BaseMessagebox.Button show(String message, BaseMessagebox.Button[] buttons, EventListener<BaseMessagebox.ClickEvent> listener) {
        return show(message, null, buttons, null, INFORMATION, null, listener, null);
    }

    /** Shows a message box and returns what button is pressed.
     *
     * @param title the title. If null, {@link WebApp#getAppName} is used.
     * @param buttons a combination of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}. If zero, {@link #OK} is assumed
     * @param icon one of predefined images: {@link #QUESTION},
     * {@link #EXCLAMATION}, {@link #ERROR}, {@link #NONE}, or any style class
     * name(s) to show an image.
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     */
    public static int show(String message, String title, int buttons, String icon) {
        return show(message, title, buttons, icon, 0, null);
    }

    /** Shows a message box and returns what button is pressed.
     *
     * @param title the title. If null, {@link WebApp#getAppName} is used.
     * @param buttons a combination of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}. If zero, {@link #OK} is assumed
     * @param icon one of predefined images: {@link #QUESTION},
     * {@link #EXCLAMATION}, {@link #ERROR}, {@link #NONE}, or any style class
     * name(s) to show an image.
     * @param listener the event listener which is invoked when a button
     * is clicked. Ignored if null.
     * It is useful if the event processing thread is disabled
     * ({@link org.zkoss.zk.ui.util.Configuration#enableEventThread}).
     * If the event processing thread is disabled, this method always
     * return {@link #OK}. To know which button is pressed, you have to pass an
     * event listener. Then, when the user clicks a button, the event
     * listener is invoked. You can identify which button is clicked
     * by examining the event name ({@link org.zkoss.zk.ui.event.Event#getName}) as shown
     * in the following table. Alternatively, you can examine the value
     * of {@link org.zkoss.zk.ui.event.Event#getData}, which must be an
     * integer representing the button, such as {@link #OK}, {@link #YES}
     * and so on.
     * <table border="1">
     *<tr><td>Button Name</td><td>Event Name</td></tr>
     *<tr><td>OK</td><td>onOK ({@link #ON_OK})</td></tr>
     *<tr><td>Cancel</td><td>onCancel ({@link #ON_CANCEL})</td></tr>
     *<tr><td>Yes</td><td>onYes ({@link #ON_YES})</td></tr>
     *<tr><td>No</td><td>onNo ({@link #ON_NO})</td></tr>
     *<tr><td>Retry</td><td>onRetry ({@link #ON_RETRY})</td></tr>
     *<tr><td>Abort</td><td>onAbort ({@link #ON_ABORT})</td></tr>
     *<tr><td>Ignore</td><td>onIgnore ({@link #ON_IGNORE})</td></tr>
     *</table>
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     * @since 3.0.4
     */
    public static int show(String message, String title, int buttons, String icon, EventListener<Event> listener) {
        return show(message, title, buttons, icon, 0, listener);
    }

    /** Shows a message box and returns what button is pressed.
     *
     * @param title the title. If null, {@link WebApp#getAppName} is used.
     * @param buttons a combination of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}. If zero, {@link #OK} is assumed
     * @param icon one of predefined images: {@link #QUESTION},
     * {@link #EXCLAMATION}, {@link #ERROR}, {@link #NONE}, or any style class
     * name(s) to show an image.
     * @param focus one of button to have to focus. If 0, the first button
     * will gain the focus. One of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}.
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     * @since 3.0.0
     */
    public static int show(String message, String title, int buttons, String icon, int focus) {
        return show(message, title, buttons, icon, focus, null);
    }

    /** Shows a message box and returns what button is pressed.
     *
     * @param title the title. If null, {@link WebApp#getAppName} is used.
     * @param buttons a combination of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}. If zero, {@link #OK} is assumed
     * @param icon one of predefined images: {@link #QUESTION},
     * {@link #EXCLAMATION}, {@link #ERROR}, {@link #NONE}, or any style class
     * name(s) to show an image.
     * @param focus one of button to have to focus. If 0, the first button
     * will gain the focus. One of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}.
     * @param listener the event listener which is invoked when a button
     * is clicked. Ignored if null.
     * It is useful if the event processing thread is disabled
     * ({@link org.zkoss.zk.ui.util.Configuration#enableEventThread}).
     * If the event processing thread is disabled, this method always
     * return {@link #OK}. To know which button is pressed, you have to pass an
     * event listener. Then, when the user clicks a button, the event
     * listener is invoked. You can identify which button is clicked
     * by examining the event name ({@link org.zkoss.zk.ui.event.Event#getName}) as shown
     * in the following table. Alternatively, you can examine the value
     * of {@link org.zkoss.zk.ui.event.Event#getData}, which must be an
     * integer representing the button, such as {@link #OK}, {@link #YES}
     * and so on.
     * <table border="1">
     *<tr><td>Button</td><td>Event Name</td></tr>
     *<tr><td>OK</td><td>onOK ({@link #ON_OK})</td></tr>
     *<tr><td>Cancel</td><td>onCancel ({@link #ON_CANCEL})</td></tr>
     *<tr><td>Yes</td><td>onYes ({@link #ON_YES})</td></tr>
     *<tr><td>No</td><td>onNo ({@link #ON_NO})</td></tr>
     *<tr><td>Retry</td><td>onRetry ({@link #ON_RETRY})</td></tr>
     *<tr><td>Abort</td><td>onAbort ({@link #ON_ABORT})</td></tr>
     *<tr><td>Ignore</td><td>onIgnore ({@link #ON_IGNORE})</td></tr>
     *</table>
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     * @since 3.0.4
     */
    public static int show(String message, String title, int buttons, String icon, int focus,
                           EventListener<Event> listener) {
        BaseMessagebox.Button res = show(message, title, toButtonTypes(buttons), null, icon, focus != 0 ? toButtonType(focus) : null,
                toButtonListener(listener), null);
        return res != null ? res.id : 0; // B60-ZK-946: NPE
    }

    /** Shows a message box and returns what button is pressed.
     * A shortcut to show(message, null, OK, INFORMATION).
     */
    public static int show(String message) {
        return show(message, null, OK, INFORMATION, 0, null);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     */
    public static int show(int messageCode, Object[] args, int titleCode, int buttons, String icon) {
        return show(messageCode, args, titleCode, buttons, icon, 0, null);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     * @param focus one of button to have to focus. If 0, the first button
     * will gain the focus. One of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}.
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     * @since 3.0.0
     */
    public static int show(int messageCode, Object[] args, int titleCode, int buttons, String icon, int focus) {
        return show(messageCode, args, titleCode, buttons, icon, focus, null);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     * @param focus one of button to have to focus. If 0, the first button
     * will gain the focus. One of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}.
     * @param listener the event listener which is invoked when a button
     * is clicked. Ignored if null.
     * It is useful if the event processing thread is disabled
     * ({@link org.zkoss.zk.ui.util.Configuration#enableEventThread}).
     * If the event processing thread is disabled, this method always
     * return {@link #OK}. To know which button is pressed, you have to pass an
     * event listener. Then, when the user clicks a button, the event
     * listener is invoked. You can identify which button is clicked
     * by examining the event name ({@link org.zkoss.zk.ui.event.Event#getName}) as shown
     * in the following table. Alternatively, you can examine the value
     * of {@link org.zkoss.zk.ui.event.Event#getData}, which must be an
     * integer representing the button, such as {@link #OK}, {@link #YES}
     * and so on.
     * <table border="1">
     *<tr><td>Button</td><td>Event Name</td></tr>
     *<tr><td>OK</td><td>onOK ({@link #ON_OK})</td></tr>
     *<tr><td>Cancel</td><td>onCancel ({@link #ON_CANCEL})</td></tr>
     *<tr><td>Yes</td><td>onYes ({@link #ON_YES})</td></tr>
     *<tr><td>No</td><td>onNo ({@link #ON_NO})</td></tr>
     *<tr><td>Retry</td><td>onRetry ({@link #ON_RETRY})</td></tr>
     *<tr><td>Abort</td><td>onAbort ({@link #ON_ABORT})</td></tr>
     *<tr><td>Ignore</td><td>onIgnore ({@link #ON_IGNORE})</td></tr>
     *</table>
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     * @since 3.0.4
     */
    public static int show(int messageCode, Object[] args, int titleCode, int buttons, String icon, int focus,
                           EventListener<Event> listener) {
        return show(Messages.get(messageCode, args), titleCode > 0 ? Messages.get(titleCode) : null, buttons, icon,
                focus, listener);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     */
    public static int show(int messageCode, Object arg, int titleCode, int buttons, String icon) {
        return show(messageCode, arg, titleCode, buttons, icon, 0, null);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     * @param focus one of button to have to focus. If 0, the first button
     * will gain the focus. One of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}.
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     * @since 3.0.0
     */
    public static int show(int messageCode, Object arg, int titleCode, int buttons, String icon, int focus) {
        return show(messageCode, arg, titleCode, buttons, icon, focus, null);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     * @param focus one of button to have to focus. If 0, the first button
     * will gain the focus. One of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}.
     * @param listener the event listener which is invoked when a button
     * is clicked. Ignored if null.
     * It is useful if the event processing thread is disabled
     * ({@link org.zkoss.zk.ui.util.Configuration#enableEventThread}).
     * If the event processing thread is disabled, this method always
     * return {@link #OK}. To know which button is pressed, you have to pass an
     * event listener. Then, when the user clicks a button, the event
     * listener is invoked. You can identify which button is clicked
     * by examining the event name ({@link org.zkoss.zk.ui.event.Event#getName}) as shown
     * in the following table. Alternatively, you can examine the value
     * of {@link org.zkoss.zk.ui.event.Event#getData}, which must be an
     * integer representing the button, such as {@link #OK}, {@link #YES}
     * and so on.
     * <table border="1">
     *<tr><td>Button</td><td>Event Name</td></tr>
     *<tr><td>OK</td><td>onOK ({@link #ON_OK})</td></tr>
     *<tr><td>Cancel</td><td>onCancel ({@link #ON_CANCEL})</td></tr>
     *<tr><td>Yes</td><td>onYes ({@link #ON_YES})</td></tr>
     *<tr><td>No</td><td>onNo ({@link #ON_NO})</td></tr>
     *<tr><td>Retry</td><td>onRetry ({@link #ON_RETRY})</td></tr>
     *<tr><td>Abort</td><td>onAbort ({@link #ON_ABORT})</td></tr>
     *<tr><td>Ignore</td><td>onIgnore ({@link #ON_IGNORE})</td></tr>
     *</table>
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     * @since 3.0.4
     */
    public static int show(int messageCode, Object arg, int titleCode, int buttons, String icon, int focus,
                           EventListener<Event> listener) {
        return show(Messages.get(messageCode, arg), titleCode > 0 ? Messages.get(titleCode) : null, buttons, icon,
                focus, listener);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     */
    public static int show(int messageCode, int titleCode, int buttons, String icon) {
        return show(messageCode, titleCode, buttons, icon, 0);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     * @param focus one of button to have to focus. If 0, the first button
     * will gain the focus. One of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}.
     * @since 3.0.0
     */
    public static int show(int messageCode, int titleCode, int buttons, String icon, int focus) {
        return show(messageCode, titleCode, buttons, icon, focus, null);
    }

    /** Shows a message box by specifying a message code, and returns what
     * button is pressed.
     *
     * @param titleCode the message code for the title. If non-positive,
     * the default title is used.
     * @param focus one of button to have to focus. If 0, the first button
     * will gain the focus. One of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}.
     * @param listener the event listener which is invoked when a button
     * is clicked. Ignored if null.
     * It is useful if the event processing thread is disabled
     * ({@link org.zkoss.zk.ui.util.Configuration#enableEventThread}).
     * If the event processing thread is disabled, this method always
     * return {@link #OK}. To know which button is pressed, you have to pass an
     * event listener. Then, when the user clicks a button, the event
     * listener is invoked. You can identify which button is clicked
     * by examining the event name ({@link org.zkoss.zk.ui.event.Event#getName}) as shown
     * in the following table. Alternatively, you can examine the value
     * of {@link org.zkoss.zk.ui.event.Event#getData}, which must be an
     * integer representing the button, such as {@link #OK}, {@link #YES}
     * and so on.
     * <table border="1">
     *<tr><td>Button</td><td>Event Name</td></tr>
     *<tr><td>OK</td><td>onOK ({@link #ON_OK})</td></tr>
     *<tr><td>Cancel</td><td>onCancel ({@link #ON_CANCEL})</td></tr>
     *<tr><td>Yes</td><td>onYes ({@link #ON_YES})</td></tr>
     *<tr><td>No</td><td>onNo ({@link #ON_NO})</td></tr>
     *<tr><td>Retry</td><td>onRetry ({@link #ON_RETRY})</td></tr>
     *<tr><td>Abort</td><td>onAbort ({@link #ON_ABORT})</td></tr>
     *<tr><td>Ignore</td><td>onIgnore ({@link #ON_IGNORE})</td></tr>
     *<tr><td>The close button on the right-top corner (x)<br>since 5.0.2</td><td>onClose</td></tr>
     *</table>
     * @return the button being pressed (one of {@link #OK}, {@link #CANCEL},
     * {@link #YES}, {@link #NO}, {@link #ABORT}, {@link #RETRY},
     * and {@link #IGNORE}).
     * Note: if the event processing thread is disabled, it always
     * returns {@link #OK}.
     * @since 3.0.4
     */
    public static int show(int messageCode, int titleCode, int buttons, String icon, int focus,
                           EventListener<Event> listener) {
        return show(Messages.get(messageCode), titleCode > 0 ? Messages.get(titleCode) : null, buttons, icon, focus,
                listener);
    }

    private static BaseMessagebox.Button toButtonType(int btn) {
        switch (btn) {
            case CANCEL:
                return BaseMessagebox.Button.CANCEL;
            case YES:
                return BaseMessagebox.Button.YES;
            case NO:
                return BaseMessagebox.Button.NO;
            case ABORT:
                return BaseMessagebox.Button.ABORT;
            case RETRY:
                return BaseMessagebox.Button.RETRY;
            case IGNORE:
                return BaseMessagebox.Button.IGNORE;
            default:
                return BaseMessagebox.Button.OK;
        }
    }

    private static BaseMessagebox.Button[] toButtonTypes(int buttons) {
        final List<BaseMessagebox.Button> btntypes = new ArrayList<BaseMessagebox.Button>();
        if ((buttons & OK) != 0)
            btntypes.add(toButtonType(OK));
        if ((buttons & CANCEL) != 0)
            btntypes.add(toButtonType(CANCEL));
        if ((buttons & YES) != 0)
            btntypes.add(toButtonType(YES));
        if ((buttons & NO) != 0)
            btntypes.add(toButtonType(NO));
        if ((buttons & RETRY) != 0)
            btntypes.add(toButtonType(RETRY));
        if ((buttons & ABORT) != 0)
            btntypes.add(toButtonType(ABORT));
        if ((buttons & IGNORE) != 0)
            btntypes.add(toButtonType(IGNORE));
        return btntypes.toArray(new BaseMessagebox.Button[btntypes.size()]);
    }

    private static EventListener<BaseMessagebox.ClickEvent> toButtonListener(EventListener<Event> listener) {
        return listener != null ? new BaseMessagebox.ButtonListener(listener) : null;
    }

    /** Sets the template used to create the message dialog.
     *
     * <p>The template must follow the default template:
     * ~./zul/html/messagebox.zul
     *
     * <p>In other words, just adjust the label and layout and don't
     * change the component's ID.
     */
    public static void setTemplate(String uri) {
        if (uri == null || uri.length() == 0)
            throw new IllegalArgumentException("empty");
        _templ = uri;
    }

    /** Returns the template used to create the message dialog.
     */
    public static String getTemplate() {
        return _templ;
    }

    /** The button types.
     * @since 6.0.0
     */
    public static enum Button {
        /** A OK button. */
        OK(BaseMessagebox.OK, ON_OK, MZul.OK, "OK"),
        /** A Cancel button. */
        CANCEL(BaseMessagebox.CANCEL, ON_CANCEL, MZul.CANCEL, "CANCEL"),
        /** A Yes button. */
        YES(BaseMessagebox.YES, ON_YES, MZul.YES, "YES"),
        /** A No button. */
        NO(BaseMessagebox.NO, ON_NO, MZul.NO, "NO"),
        /** A Abort button. */
        ABORT(BaseMessagebox.ABORT, ON_ABORT, MZul.ABORT, "ABORT"),
        /** A Retry button. */
        RETRY(BaseMessagebox.RETRY, ON_RETRY, MZul.RETRY, "RETRY"),
        /** A IGNORE button. */
        IGNORE(BaseMessagebox.IGNORE, ON_IGNORE, MZul.IGNORE, "IGNORE");

        /** The unique ID to represent this button type. */
        public final int id;
        /** The event name associated with this button type. */
        public final String event;
        /** The message ID used for loading the label.
         * @see Messages
         */
        public final int label;
        /** Used for backward compatibility to ZK 5. */
        private final String stringId;

        private Button(int id, String event, int label, String stringId) {
            this.id = id;
            this.event = event;
            this.label = label;
            this.stringId = stringId;
        }
    }

    /** The event that will be received by the listener when the user clicks a button.
     * @since 6.0.0
     */
    public static class ClickEvent extends Event {
        public ClickEvent(String name, Component target, BaseMessagebox.Button button) {
            super(name, target, button);
        }

        /** Returns the button being clicked. If the close button on the
         * title is clicked, this method returns null (and {@link #getName} returns
         * onClose).
         */
        public BaseMessagebox.Button getButton() {
            return (BaseMessagebox.Button) getData();
        }
    }

    private static class ButtonListener implements SerializableEventListener<BaseMessagebox.ClickEvent> {
        private final EventListener<Event> _listener;

        private ButtonListener(EventListener<Event> listener) {
            _listener = listener;
        }

        public void onEvent(BaseMessagebox.ClickEvent event) throws Exception {
            final BaseMessagebox.Button btn = event.getButton();
            _listener.onEvent(new Event(event.getName(), event.getTarget(), btn != null ? btn.id : -1));
        }

        public String toString() {
            return _listener.toString();
        }
    }
}
