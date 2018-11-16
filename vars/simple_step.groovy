import com.shurik.PrintParameters

def call( Map parameters = [:] ) {
    def stepName = 'Simple Step'

    // Can't throw this exception in sandbox
    //if (! parameters.tool ) throw new IllegalArgumentException("The parameter 'tool' can not be null or empty.")

    if (! parameters.containsKey('mandatory_param1')) {
        error "The [mandatory_param1] parameter doesn't exists"
    }

    // the way how to check the optional parameter
    parameters['optional_param1'] = parameters.optional_param1 ?: 'default value'
    parameters['pre_hook'] = parameters.pre_hook ?: ''
    parameters['post_hook'] = parameters.post_hook ?: ''

    timestamps {
        pr = new PrintParameters(this, stepName)
        pr.logParameters( parameters )

        // calling pre-hook
        if (parameters['pre_hook'].trim() ) {
            echo "[DEBUG][${stepName}] Running pre hook [${parameters['pre_hook']}]"
            evaluate( parameters['pre_hook'] )
        }
        else {
            echo "[DEBUG][${stepName}] The pre hook is empty. Skip pre hook execution."
        }

        // main
        echo "[DEBUG][${stepName}] step execution ..."

        if (parameters['post_hook'].trim() ) {
            echo "[DEBUG][${stepName}] Running post hook [${parameters['post_hook']}]"
            evaluate( parameters['post_hook'] )
        }
        else {
            echo "[DEBUG][${stepName}] The post hook is empty. Skip post hook execution."
        }
    }
}
