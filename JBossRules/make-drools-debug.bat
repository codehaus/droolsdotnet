call del drools-3.0.dll
call del drools-3.0.pdb
call ant clean-all build-all -Dtest.skip=true
call ikvmc -target:library -out:drools-3.0.dll -debug target\drools-core-3.0.2.jar target\drools-compiler-3.0.2.jar target\drools-decisiontables-3.0.2.jar -reference:drools-dep.dll
pause