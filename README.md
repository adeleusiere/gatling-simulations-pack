# gatling-simulations-pack
Simultations files from presentation "Load Tests 101!" in CF Camp 2016

Examples:
> JAVA_OPTS="-Dduration=15 -Dusers=1 -Dbase=cf.local" ./gatling.sh -s cfcamp2016.ConstantSimulation
> JAVA_OPTS="-Dduration=60 -Dusers=120 -Dbase=cf.local" ./gatling.sh -s cfcamp2016.RampSimulation
