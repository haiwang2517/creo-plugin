(Dialog TestDemoAlt
    (Components
        (SubLayout Content)
        (Separator CommitSeparator)
        (PushButton CommitOK)
        (PushButton CommitCancel)
    )
    (Resources
        (CommitSeparator.ResourceHints "Template:OKCancelDialog.CommitSeparator")
        (CommitOK.Label "OK")
        (CommitOK.TopOffset 10)
        (CommitOK.BottomOffset 10)
        (CommitOK.LeftOffset 40)
        (CommitOK.RightOffset 0)
        (CommitOK.ResourceHints "Template:OKCancelDialog.CommitOK")
        (CommitCancel.Label "Cancel")
        (CommitCancel.TopOffset 10)
        (CommitCancel.BottomOffset 10)
        (CommitCancel.LeftOffset 8)
        (CommitCancel.RightOffset 10)
        (CommitCancel.ResourceHints "Template:OKCancelDialog.CommitCancel")
        (.TopOffset 0)
        (.BottomOffset 0)
        (.LeftOffset 0)
        (.RightOffset 0)
        (.DefaultButton "CommitOK")
        (.ResourceHints "Template:OKCancelDialog" "Guidelines:Creo4-Dialog" "GuidelinesVersion:1" "Version:Creo4" "@Subgrid0x2.Template:OKCancelDialog.CommitBar")
        (.Accelerator "Esc")
        (.AttachLeft True)
        (.Layout
            (Grid
                (Rows 1 0 0)
                (Cols 1)
                Content CommitSeparator
                (Grid
                    (Rows 1)
                    (Cols 1 0 0)
                    (Pos 1 2)
                    CommitOK
                    (Pos 1 3)
                    CommitCancel
                )
            )
        )
    )
)

(Layout Content
    (Components
        (Label ReplaceThis)
    )
    (Resources
        (ReplaceThis.Label "

        Replace This With Your Content        


")
        (ReplaceThis.AttachLeft True)
        (ReplaceThis.BackgroundColor 25685245)
        (ReplaceThis.TopOffset 0)
        (ReplaceThis.BottomOffset 0)
        (ReplaceThis.LeftOffset 0)
        (ReplaceThis.RightOffset 0)
        (ReplaceThis.ResourceHints "Template:OKCancelDialog.ReplaceThis")
        (.AttachLeft True)
        (.AttachRight True)
        (.AttachTop True)
        (.TopOffset 16)
        (.BottomOffset 16)
        (.LeftOffset 10)
        (.RightOffset 10)
        (.ResourceHints "Template:OKCancelDialog.Content")
        (.AttachBottom True)
        (.Layout
            (Grid
                (Rows 0)
                (Cols 0)
                ReplaceThis
            )
        )
    )
)
