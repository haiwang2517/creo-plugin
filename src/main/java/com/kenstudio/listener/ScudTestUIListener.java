package com.kenstudio.listener;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.uifc.uifcComponent.uifcComponent;
import com.ptc.uifc.uifcDialog.DefaultDialogListener;
import com.ptc.uifc.uifcDialog.Dialog;
import com.ptc.uifc.uifcDialog.uifcDialog;
import com.ptc.uifc.uifcPushButton.DefaultPushButtonListener;
import com.ptc.uifc.uifcPushButton.PushButton;
import com.ptc.uifc.uifcPushButton.uifcPushButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScudTestUIListener extends DefaultUICommandActionListener {
    public static final String DIALOG_TITLE = "ScudTestUIInstance";
    Logger logger = LoggerFactory.getLogger(ScudTestUIListener.class);

    @Override
    public void OnCommand() throws jxthrowable {
        logger.info("execution command");
        try {
            uifcComponent.CreateDialog(DIALOG_TITLE, "ScudTestUI");

            Dialog scudtestui = uifcDialog.DialogFind(DIALOG_TITLE, "ScudTestUI");
            scudtestui.AddActionListener(new ScudTestUIDialogListener());

            PushButton commitok = uifcPushButton.PushButtonFind(DIALOG_TITLE, "CommitOK");
            CommitOKPushButtonListener commitokLis = new CommitOKPushButtonListener();
            commitok.AddActionListener(commitokLis);

            PushButton commitcancel = uifcPushButton.PushButtonFind(DIALOG_TITLE, "CommitCancel");
            CommitCancelPushButtonListener commitcancelLis = new CommitCancelPushButtonListener();
            commitcancel.AddActionListener(commitcancelLis);
        } catch (Exception e){
            logger.error("ScudTestUIListener error", e);
        }

        logger.info("execution show");
        int result = uifcComponent.ActivateDialog (DIALOG_TITLE);
        logger.info("execution show result: {}", result);
        logger.info("24");
        uifcComponent.DestroyDialog (DIALOG_TITLE);
    }

    class CommitOKPushButtonListener extends DefaultPushButtonListener {
        CommitOKPushButtonListener() {}

        public void OnActivate(PushButton handle) {

            logger.info("CommitOKPushButtonListener");
        }
    }


    class CommitCancelPushButtonListener extends DefaultPushButtonListener {
        CommitCancelPushButtonListener() {}

        public void OnActivate(PushButton handle) {
            logger.info("CommitCancelPushButtonListener");
            try {
                logger.info("OnClose1");
                uifcComponent.ExitDialog(DIALOG_TITLE, 0);
            } catch (Throwable e) {
                logger.error("close error", e);
            }
        }
    }


    class ScudTestUIDialogListener extends DefaultDialogListener {
        ScudTestUIDialogListener() {}

        public void OnClose(Dialog handle) {
            try {
                logger.info("OnClose");
                uifcComponent.ExitDialog(DIALOG_TITLE, 0);
            } catch (Throwable e) {
                logger.error("close error", e);
            }
        }

        public void OnEndMoveSize(Dialog handle) {}
    }
}
