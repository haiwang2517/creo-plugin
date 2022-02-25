package com.kenstudio.listener;

import com.kenstudio.cos.COSConfig;
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
            COSConfig config = new COSConfig();
            Session session =  pfcSession.GetCurrentSession();
            session.UIShowMessageDialog(config.toString(), null);
        } catch (jxthrowable e) {
            e.printStackTrace();
        }
    }

}
