(Dialog ScudTestUI
    (Components
        (SubLayout scud_top)
        (SubLayout button_groups)
    )
    (Resources
        (.Label "scud test ui")
        (.ResourceHints "Version:Creo4")
        (.Focus "InputPanel1")
        (.DefaultButton "CommitOK")
        (.Layout
            (Grid
                (Rows 1 0)
                (Cols 1)
                scud_top button_groups
            )
        )
    )
)
(Layout scud_top
    (Components
        (SubLayout scud_one)
    )
    (Resources
        (.Label "plan valid")
        (.Layout
            (Grid
                (Rows 1)
                (Cols 1)
                scud_one
            )
        )
    )
)
(Layout scud_one
    (Components
        (Label Label1)
        (InputPanel InputPanel1)
        (Label Label2)
        (InputPanel InputPanel2)
    )
    (Resources
        (Label1.Label "input1")
        (Label2.Label "input2")
        (.Label "plan test")
        (.Decorated 1)
        (.Layout
            (Grid
                (Rows 1 0)
                (Cols 1 0)
                Label1 InputPanel1 Label2 InputPanel2
            )
        )
    )
)
(Layout button_groups
    (Components
        (PushButton CommitOK)
        (PushButton CommitCancel)
    )
    (Resources
        (CommitOK.Label "confirm")
        (CommitCancel.Label "cancel")
        (.Label "button goups")
        (.Layout
            (Grid
                (Rows 1)
                (Cols 1 0)
                CommitOK CommitCancel
            )
        )
    )
)
