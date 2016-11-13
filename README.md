# gatling-simulations-pack
Simultations files from presentation "Load Tests 101!" in CF Camp 2016

1. download Gatling from http://gatling.io/#/resources/download
2. copy the provided scala files to the folder user-files/simulations
3. you're ready to load test!

Examples of simulation run:
> JAVA_OPTS="-Dduration=15 -Dusers=1 -Dbase=cf.local" ./gatling.sh -s cfcamp2016.ConstantSimulation
> JAVA_OPTS="-Dduration=60 -Dusers=120 -Dbase=cf.local" ./gatling.sh -s cfcamp2016.RampSimulation
