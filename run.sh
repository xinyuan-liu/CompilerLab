for(( i = 0;i<15;i++)) do
    ./run_typecheck.sh <test$i.java | java -jar pgi.jar
done
