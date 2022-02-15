package com.kenstudio.component;

import com.ptc.pfc.pfcCommand.DefaultUICommandActionListener;

public class Button {

    String commandCode;
    String icon;
    String label;
    String help;
    String description;
    String messageFileName;
    DefaultUICommandActionListener actionListener;

    private Button(Builder builder){
        this.commandCode = builder.commandCode;
        this.icon = builder.icon;
        this.label = builder.label;
        this.help = builder.help;
        this.description = builder.description;
        this.messageFileName = builder.messageFileName;
        this.actionListener = builder.actionListener;
    }

    public static class Builder {
        String commandCode;
        String icon;
        String label;
        String help;
        String description;
        String messageFileName;
        DefaultUICommandActionListener actionListener;

        public Builder actionListener(DefaultUICommandActionListener actionListener){
            this.actionListener = actionListener;
            return this;
        }
        public Builder commandCode(String commandCode){
            this.commandCode = commandCode;
            return this;
        }

        public Builder icon(String icon){
            this.icon = icon;
            return this;
        }
        public Builder label(String label){
            this.label = label;
            return this;
        }
        public Builder help(String help){
            this.help = help;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder messageFileName(String messageFileName){
            this.messageFileName = messageFileName;
            return this;
        }
        public Button build(){
            return  new Button(this);
        }
    }
}
