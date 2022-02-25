package com.kenstudio.listener;

import com.kenstudio.dialog.ScudSettingUIDialog;
import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;

public class ScudSettingUIListener extends DefaultUICommandActionListener {

    @Override
    public void OnCommand() throws jxthrowable {
        // 加载Setting UI
        ScudSettingUIDialog settingUIDialog = new ScudSettingUIDialog();
        settingUIDialog.show();
    }
}
