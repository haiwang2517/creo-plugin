package com.kenstudio.component;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.UICommand;
import com.ptc.pfc.pfcSession.CreoCompatibility;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

public class UICommandFactory {

    public static void load(final Session session, Button button) throws jxthrowable {
        UICommand uICommand =  session.UICreateCommand(button.commandCode, button.actionListener);
        uICommand.SetIcon(button.icon);
        uICommand.Designate(button.messageFileName, button.label, button.help, button.description);
    }


}
