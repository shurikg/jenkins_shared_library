# General
* The shared library should be independent to other shared libraries
* The shared library version should be done with semantic versioning rules (https://semver.org) 
* The shared library doesn’t need to define any global variables it it’s not require (env.XXX)
* The shared library should run in sandbox, avoid special security approval
* The shared library code should not contains any hard coded values. The hardcoded values can be the default value for optional parameters.

# Method Names
* The method name should be unique for all shared libraries in Jenkins.
* The method name can not be with the same name as other Jenkins commands (e.g. build)
* The method name should to describe what the function do.
* The method name should to start with shared library prefix
>e.g. the shared library for FOSS
```
    foss_trigger_scan
    foss_analize_scan_result
```
# Method parameters
* The method should get parameters as the Map object
```groovy 
    def call ( Map parameters = [:] ){ … }
```
* The mandatory parameters should be check in the method and failed if it’s not define
```groovy
    if (! parameters.containsKey('mandatory_param1')) {
          error "The [mandatory_param1] parameter doesn't exists" 
    }
```
* The optional parameters should be check in the method and if it’s not define, set the default value
```groovy
    parameters['optional_param1'] = parameters.optional_param1 ?: 'default value’
```
* The method can have pre/post hook for customization options *.

> Note: the pre/post hook can provide security problem if you define the shared library in global and it will run not in the sandbox

# Logging
* The method should print all method parameters and values (not sensitive data)
* The method should print enough information in order to understand what happened
* The message should be with correct severity
* The message should contain the name of the method
* The timestamp should be implement
* Control the severity output via configuration

# Documentation
* Each global method should have the txt file with method description
* The txt file should be well format (html) in order to see it correct via Jenkins
>[Optional]
	Generate the markdown site with all global method documentation
	The site will be generated as part of release process

# Testing
* The shared library should contain the test framework
* The tests should run and pass before release

# Release
The release should contains
* Success tests execution
* Release notes
* Artifact of shared library
* Artifact of documentation ?
* The list of plugins that shared library require
