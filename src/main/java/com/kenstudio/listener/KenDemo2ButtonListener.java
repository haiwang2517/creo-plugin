package com.kenstudio.listener;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

public class KenDemo2ButtonListener extends DefaultUICommandActionListener {

    @Override
    public void OnCommand() throws jxthrowable {
        commandListenerHandle();
    }

    public void commandListenerHandle() {
        try {
            Session session =  pfcSession.GetCurrentSession();
            session.UIShowMessageDialog("sssssssssss22", null);
        } catch (jxthrowable e) {
            e.printStackTrace();
        }
    }

}
