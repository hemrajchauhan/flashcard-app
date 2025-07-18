package com.hemrajchauhan.flashcardapp.ui;

import javafx.scene.control.ChoiceDialog;
import java.util.List;
import java.util.Optional;

public class TableSelectorDialog {
    public static Optional<String> selectTable(List<String> tables){
        ChoiceDialog<String> dialog = new ChoiceDialog<>(tables.isEmpty() ? null : tables.get(0), tables);
        dialog.setTitle("Select Table");
        dialog.setHeaderText("Choose a table to use for your flashcards:");
        return dialog.showAndWait();
    }

    public static Optional<String> selectColumn(List<String> columns , String side){
        ChoiceDialog<String> dialog = new ChoiceDialog<>(columns.isEmpty() ? null : columns.get(0), columns);
        dialog.setTitle("Select Column");
        dialog.setHeaderText("Choose the column to use as the " + side + ":");
        return dialog.showAndWait();
    }
}
