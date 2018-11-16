package com.shurik

class PrintParameters implements Serializable {
    def script
    def stepName

    PrintParameters(script, String stepName) {
        this.script = script
        this.stepName = stepName
    }

    def logParameters( Map parameters ){
        def message = "[DEBUG][${this.stepName}] Method parameters:\n"
        parameters.each{ k, v -> message = message + "[DEBUG][${this.stepName}]      ${k}=[${v}] \n" }
        this.script.echo message
    }
}
