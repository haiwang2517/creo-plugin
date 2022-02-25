(Dialog SucdSettingUI
    (Components
        (SubLayout top)
        (SubLayout buttons)
    )
    (Resources
        (.Label "同步信息配置")
        (.ResourceHints "Version:Creo4")
        (.MinColumns 20)
        (.Layout
            (Grid
                (Rows 1 0)
                (Cols 1)
                top buttons
            )
        )
    )
)

(Layout top
    (Components
        (SubLayout Layout1)
        (SubLayout Layout2)
        (Table BucketTable)
        (SubLayout BucketButtons)
    )
    (Resources
        (BucketTable.SelectionPolicy 4)
        (BucketTable.TopOffset 5)
        (BucketTable.AutoHighlight True)
        (BucketTable.ColumnNames "objectName" "objectSize" "objectModifyDate")
        (BucketTable.ColumnLabels "文件名" "大小" "修改时间")
        (BucketTable.RowSelectionPolicy 4)
        (.Columns 18)
        (.MinColumns 18)
        (.Layout
            (Grid
                (Rows 1 0 0 0)
                (Cols 1)
                Layout1 Layout2 BucketTable BucketButtons
            )
        )
    )
)

(Layout Layout1
    (Components
        (Label SettingSavePathLabel)
        (InputPanel SettingSavePath)
        (PushButton SettingSavePtahSearch)
    )
    (Resources
        (SettingSavePathLabel.Label "本地路径：")
        (SettingSavePathLabel.Columns 5)
        (SettingSavePathLabel.Alignment 0)
        (SettingSavePath.Sensitive False)
        (SettingSavePath.MaxLen 3000)
        (SettingSavePath.Value "1231231231cxssssssssssssssssssssssq123123s")
        (SettingSavePath.InputType 0)
        (SettingSavePath.AutoHighlight True)
        (SettingSavePtahSearch.Label "浏览...")
        (SettingSavePtahSearch.Columns 5)
        (SettingSavePtahSearch.Alignment 2)
        (SettingSavePtahSearch.LeftOffset 2)
        (.Label "设置")
        (.Columns 18)
        (.Decorated 2)
        (.Layout
            (Grid
                (Rows 1)
                (Cols 1 0 0)
                SettingSavePathLabel SettingSavePath SettingSavePtahSearch
            )
        )
    )
)

(Layout Layout2
    (Components
        (Label CosBucketLabel)
        (InputPanel CosBucket)
        (Label CosRegionLabel)
        (InputPanel CosRegion)
        (PushButton CommitOK)
    )
    (Resources
        (CosBucketLabel.Label "Bucket：")
        (CosBucketLabel.Columns 5)
        (CosBucket.Columns 21)
        (CosBucket.MaxLen 100)
        (CosBucket.InputType 0)
        (CosRegionLabel.Label "Region：")
        (CosRegionLabel.Columns 5)
        (CosRegionLabel.TopOffset 2)
        (CosRegion.Columns 21)
        (CosRegion.MaxLen 100)
        (CosRegion.TopOffset 2)
        (CosRegion.InputType 0)
        (CommitOK.Label "连接")
        (CommitOK.TopOffset 2)
        (.Label "COS配置")
        (.Columns 18)
        (.Decorated 1)
        (.Layout
            (Grid
                (Rows 1 0 0)
                (Cols 1 0)
                CosBucketLabel CosBucket CosRegionLabel CosRegion CommitOK
            )
        )
    )
)

(Layout BucketButtons
    (Components
        (PushButton DownloadButton)
        (ProgressBar DownladProgressBar)
        (PushButton OpenDownloadFileButton)
    )
    (Resources
        (DownloadButton.Label "下载")
        (DownloadButton.IllegalDragCursor "UI arrow cursor image")
        (DownladProgressBar.MinInteger 0)
        (DownladProgressBar.MaxInteger 100)
        (OpenDownloadFileButton.Label "打开")
        (OpenDownloadFileButton.Sensitive False)
        (.Label "操作区")
        (.Columns 27)
        (.Decorated 1)
        (.Layout
            (Grid
                (Rows 1)
                (Cols 1 1 0)
                DownloadButton DownladProgressBar OpenDownloadFileButton
            )
        )
    )
)

(Layout buttons
    (Components
        (PushButton CommitCancel)
    )
    (Resources
        (CommitCancel.Label "取消")
        (.Alignment 2)
        (.BottomOffset 2)
        (.Layout
            (Grid
                (Rows 1)
                (Cols 0)
                CommitCancel
            )
        )
    )
)
