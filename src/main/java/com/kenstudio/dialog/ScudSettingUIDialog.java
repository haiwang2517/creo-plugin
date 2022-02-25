package com.kenstudio.dialog;

import com.kenstudio.cos.COSFactory;
import com.kenstudio.cos.COSTransferManager;
import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcModel.ModelDescriptor;
import com.ptc.pfc.pfcModel.pfcModel;
import com.ptc.pfc.pfcSession.Session;
import com.ptc.pfc.pfcSession.pfcSession;
import com.ptc.pfc.pfcUI.pfcUI;
import com.ptc.uifc.uifcComponent.uifcComponent;
import com.ptc.uifc.uifcCore.RowPositionData;
import com.ptc.uifc.uifcCore.uifcCore;
import com.ptc.uifc.uifcDialog.DefaultDialogListener;
import com.ptc.uifc.uifcDialog.Dialog;
import com.ptc.uifc.uifcDialog.uifcDialog;
import com.ptc.uifc.uifcInputPanel.InputPanel;
import com.ptc.uifc.uifcInputPanel.uifcInputPanel;
import com.ptc.uifc.uifcProgressBar.ProgressBar;
import com.ptc.uifc.uifcProgressBar.uifcProgressBar;
import com.ptc.uifc.uifcPushButton.DefaultPushButtonListener;
import com.ptc.uifc.uifcPushButton.PushButton;
import com.ptc.uifc.uifcPushButton.uifcPushButton;
import com.ptc.uifc.uifcTable.Table;
import com.ptc.uifc.uifcTable.TableCell;
import com.ptc.uifc.uifcTable.TableRow;
import com.ptc.uifc.uifcTable.uifcTable;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ScudSettingUIDialog {

    public static final String DIALOG_NAME = "SucdSettingUI";

    static Logger logger = LoggerFactory.getLogger(ScudSettingUIDialog.class);

    public void show() {
        // 加载视图模板到内存中
        try {
            uifcComponent.CreateDialog(DIALOG_NAME, DIALOG_NAME);
            // 设置对应的事件
            uifcDialog
                    .DialogFind(DIALOG_NAME, DIALOG_NAME)
                    .AddActionListener(new SucdSettingUIDialogListener());
            uifcPushButton
                    .PushButtonFind(DIALOG_NAME, "SettingSavePtahSearch")
                    .AddActionListener(new SettingSavePtahSearchPushButtonListener());
            uifcPushButton
                    .PushButtonFind(DIALOG_NAME, "CommitOK")
                    .AddActionListener(new CommitOKPushButtonListener());
            uifcPushButton
                    .PushButtonFind(DIALOG_NAME, "CommitCancel")
                    .AddActionListener(new CommitCancelPushButtonListener());
            uifcPushButton
                    .PushButtonFind(DIALOG_NAME, "DownloadButton")
                    .AddActionListener(new DownloadButtonPushButtonListener());
            uifcPushButton
                    .PushButtonFind(DIALOG_NAME, "OpenDownloadFileButton")
                    .AddActionListener(new OpenDownloadFileButtonPushButtonListener());

            // 设置初始值
            InputPanel settingsavepath = uifcInputPanel.InputPanelFind(DIALOG_NAME, "SettingSavePath");
            settingsavepath.SetTextValue("");


            InputPanel cosBucket = uifcInputPanel.InputPanelFind(DIALOG_NAME, "CosBucket");
            cosBucket.SetTextValue("scud-1256849561");

            InputPanel cosRegion = uifcInputPanel.InputPanelFind(DIALOG_NAME, "CosRegion");
            cosRegion.SetTextValue("ap-guangzhou");

            // 激活视图
            uifcComponent.ActivateDialog (DIALOG_NAME);
            uifcComponent.DestroyDialog (DIALOG_NAME);
        } catch (jxthrowable e) {
            logger.error("load SucdSettingUI error", e);
        }
    }

    private void loadTableData(Table serverTable, int i, COSObjectSummary cosObjectSummary) throws jxthrowable {
        // 初始化行
        String rowDefined = "row" + i;
        TableRow serverRow = uifcTable.TableRowDefine(rowDefined);
        RowPositionData rowPos = uifcCore.RowPositionData_Create();
        rowPos.SetIndex(i+1);
        serverTable.InsertRow(serverRow, rowPos);

        // 列添加对应的值
        uifcTable.TableCellFind(serverTable.GetDialog(), serverTable.GetComponent(), rowDefined, "objectName").SetText(cosObjectSummary.getKey());
        uifcTable.TableCellFind(serverTable.GetDialog(), serverTable.GetComponent(), rowDefined, "objectSize").SetText(cosObjectSummary.getSize()+"");
        uifcTable.TableCellFind(serverTable.GetDialog(), serverTable.GetComponent(), rowDefined, "objectModifyDate").SetText(cosObjectSummary.getLastModified().toString());
    }

    private class SucdSettingUIDialogListener extends DefaultDialogListener {
        SucdSettingUIDialogListener() {}

        public void OnClose(Dialog handle) {
            try {
                logger.info("onClose");
                uifcComponent.ExitDialog(DIALOG_NAME, 0);
            } catch (Throwable e) {
                logger.error("onClose error", e);
            }
        }
    }

    private class SettingSavePtahSearchPushButtonListener extends DefaultPushButtonListener {
        SettingSavePtahSearchPushButtonListener() {}

        public void OnActivate(PushButton handle) {
            logger.info("search button handle");
            try {
                String ret = pfcSession.GetCurrentSession().UISelectDirectory(pfcUI.DirectorySelectionOptions_Create());
                logger.info("select dir : {}", ret);
                uifcInputPanel.InputPanelFind(DIALOG_NAME, "SettingSavePath").SetTextValue(ret);
            } catch (jxthrowable e) {
                e.printStackTrace();
            }

        }
    }

    private class CommitOKPushButtonListener extends DefaultPushButtonListener {
        CommitOKPushButtonListener() {}

        public void OnActivate(PushButton handle) {
            logger.info("commitOK button handle");
            COSClient instance = COSFactory.getInstance();
            try {
                // 连接 cos 获取桶中数据列表
                ObjectListing cosBucket = instance.listObjects(uifcInputPanel.InputPanelFind(DIALOG_NAME, "CosBucket").GetTextValue());

                // 加载到 table 中
                Table serverTable = uifcTable.TableFind(DIALOG_NAME, "BucketTable");
                for (int i = 0; i < cosBucket.getObjectSummaries().size(); i++) {
                    loadTableData(serverTable, i, cosBucket.getObjectSummaries().get(i));
                }

            } catch (jxthrowable e) {
                e.printStackTrace();
            } finally{
                instance.shutdown();
            }
        }
    }


    // TODO 1
    private  class DownloadButtonPushButtonListener extends DefaultPushButtonListener {
        DownloadButtonPushButtonListener() {}

        public void OnActivate(PushButton handle) {
            try{
                // 获取 table选择的数据，如果没有选择就提示不处理
                Table serverTable = uifcTable.TableFind(DIALOG_NAME, "BucketTable");
                TableCell wsCell =  uifcTable.TableCellFind(serverTable.GetDialog(), serverTable.GetComponent(),serverTable.GetSelectedCellNameArray().get(0), "objectName");
                String fileName = wsCell.GetText();
                // 获取 下载位置，如果不符合格式不处理
                String settingSavePath = uifcInputPanel.InputPanelFind(DIALOG_NAME, "SettingSavePath").GetTextValue();
                String localFilePath = settingSavePath + "\\" + fileName;
                // 拼接下载地址，通过下载API下载，并显示下载进度
                COSTransferManager cosTransferManager = new COSTransferManager();
                TransferManager transferManager = cosTransferManager.createTransferManager();
                GetObjectRequest getObjectRequest = new GetObjectRequest(uifcInputPanel.InputPanelFind(DIALOG_NAME, "CosBucket").GetTextValue(), fileName);

                try {
                    File downloadFile = new File(localFilePath);
                    Download download = transferManager.download(getObjectRequest, downloadFile);
                    // 打印上传进度，直到上传结束
                    ProgressBar downladProgressBar = uifcProgressBar.ProgressBarFind(DIALOG_NAME, "DownladProgressBar");
                    // 提示下载结果
                    cosTransferManager.showTransferProgress(download, downladProgressBar);
                    download.waitForCompletion();
                    pfcSession.GetCurrentSession().UIShowMessageDialog(fileName + " 文件下载完成.", null);
                    // 开启打开按钮
                    uifcPushButton.PushButtonFind(DIALOG_NAME, "OpenDownloadFileButton").SetEnabled(true);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    cosTransferManager.shutdownTransferManager(transferManager);
                }
            } catch (jxthrowable e) {
                e.printStackTrace();
            }

        }
    }

    // TODO 2
    private  class OpenDownloadFileButtonPushButtonListener extends DefaultPushButtonListener {
        OpenDownloadFileButtonPushButtonListener() {}

        public void OnActivate(PushButton handle) {
            try {
                // 获取下载路径
                Table serverTable = uifcTable.TableFind(DIALOG_NAME, "BucketTable");
                TableCell wsCell =  uifcTable.TableCellFind(serverTable.GetDialog(), serverTable.GetComponent(),serverTable.GetSelectedCellNameArray().get(0), "objectName");
                String fileName = wsCell.GetText();
                // 打开文件api
                Session session = pfcSession.GetCurrentSession();
                ModelDescriptor modelDescriptor =
                        pfcModel.ModelDescriptor_CreateFromFileName(fileName);

                Model model = session.GetModelFromDescr(modelDescriptor);
                if (model == null) {
                    model = session.RetrieveModel(modelDescriptor);
                }

                session.CreateModelWindow(model);
                model.Display();
            } catch (jxthrowable e) {
                logger.error("open file error", e);
            }
        }
    }

    private class CommitCancelPushButtonListener extends DefaultPushButtonListener {
        CommitCancelPushButtonListener() {}

        public void OnActivate(PushButton handle) {
            try {
                logger.info("commitCancel button handle");
                uifcComponent.ExitDialog(DIALOG_NAME, 0);
            } catch (Throwable e) {
                logger.error("commitCancel onClose error", e);
            }
        }
    }
}
