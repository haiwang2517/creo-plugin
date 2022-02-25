package com.kenstudio.listener;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;

public class KenDemoButtonListener extends DefaultUICommandActionListener {
    private String modelName;

    @Override
    public void OnCommand() throws jxthrowable {
        commandListenerHandle();
    }

    public void commandListenerHandle() {
        try {
            Session session = pfcSession.GetCurrentSession();

            // ModelDescriptor_CreateFromFileName 需要有后缀               ModelDescriptor_Create 需要没有后缀的
            ModelDescriptor modelDescriptor =
                    pfcModel.ModelDescriptor_CreateFromFileName("抽壳01.prt");
            //                    pfcModel.ModelDescriptor_Create(ModelType.MDL_PART, "抽壳01", "");

            Model model = session.GetModelFromDescr(modelDescriptor);
            if (model == null) {
                model = session.RetrieveModel(modelDescriptor);
            }

            session.CreateModelWindow(model);
            model.Display();
            session.UIShowMessageDialog(" 已打开.", null);
        } catch (jxthrowable e) {
            e.printStackTrace();
        }
    }
}
