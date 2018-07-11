/**
 * Name of library step
 *
 * @param mandatory param1    Description
 * @param mandatory param2    Description
 *
 * @param optional param1    Optional. Description. Default value is xxxx
 * @param optional param2    Optional. Description. Default value is xxxx
 * @param pre_hook           Optional. If the value is not empty the code will be evaluated and executed before the main code is calling. Default value is empty.
 * @param post_hook          Optional. If the value is not empty the code will be evaluated and executed after the main code is calling. Default value is empty.
 *
 *
 * @return nothing
 *
 * Example
 *
 *  stepname param1: 'xxx', param2: 'xxx'
 *
 */
def call(Map parameters = [:]) {
    def stepName = 'CHANGE ME'
    
    // check mandatory parameters
    if (! parameters.containsKey('mandatory_param1')) {
       error "The [mandatory_param1] parameter doesn't exists" 
    }
    
    // set optional default values
    parameters['optional_param1'] = parameters.optional_param1 ?: 'default value'
    
    // hook definition
    parameters['pre_hook'] = parameters.pre_hook ?: ''
    parameters['post_hook'] = parameters.post_hook ?: ''

    timestamps {
        // print the method parameters
        pr = new PrintParameters(this, stepName)
        pr.logParameters( parameters )
        
        // calling pre-hook
        if (parameters['pre_hook'].trim() ) {
            echo "[DEBUG][${stepName}] Running pre hook ${parameters['pre_hook']}"
            evaluate( parameters['pre_hook'] )
        }
        else {
            echo "[DEBUG][${stepName}] The pre hook is empty. Skip pre hook execution." 
        }
        
        // main
        echo "[DEBUG][${stepName}] step execution ..."
        
        // calling post-hook  
        if (parameters['post_hook'].trim() ) {
            echo "[DEBUG][${stepName}] Running post hook ${parameters['post_hook']}"
            evaluate( parameters['post_hook'] )
        }
        else {
            echo "[DEBUG][${stepName}] The post hook is empty. Skip post hook execution." 
        }
    }    
}
