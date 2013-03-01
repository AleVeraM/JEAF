/*
* Copyright (C) 2010 Grupo Integrado de Ingeniería
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/ 

package es.udc.gii.common.eaf.log;

import es.udc.gii.common.eaf.algorithm.EvolutionaryAlgorithm;
import es.udc.gii.common.eaf.algorithm.population.Individual;
import es.udc.gii.common.eaf.algorithm.productTrader.IndividualsProductTrader;
import es.udc.gii.common.eaf.algorithm.productTrader.specification.BestIndividualSpecification;
import java.util.List;
import java.util.Observable;
import org.apache.commons.configuration.Configuration;

/**
 * This log tool implements a log tool that record the number of function evaluations performed and the
 * best individual after the replace stage of the algorithm. This log tool does not need any
 * kind of configuration, apart from the configuration need in the superclass.
 * 
 * @author Grupo Integrado de Ingeniería (<a href="http://www.gii.udc.es">www.gii.udc.es</a>)
 * @since 1.0
 */
public class BestFEsLogTool extends LogTool {

    private int num_prints = 0;
    private List fes_prints;
    private int fes_index = 0;

    @Override
    public void configure(Configuration conf) {
        try {
            super.configure(conf);
            this.fes_prints = conf.getList("Fes_Prints");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        EvolutionaryAlgorithm algorithm = (EvolutionaryAlgorithm) o;
        BestIndividualSpecification bestSpec =
                new BestIndividualSpecification();
        Individual best;
        int algorithm_fes;

        super.update(o, arg);

        best = IndividualsProductTrader.get(bestSpec,
                algorithm.getPopulation().getIndividuals(), 1, algorithm.getComparator()).get(0);


        if (algorithm.getState() == EvolutionaryAlgorithm.REPLACE_STATE) {

            algorithm_fes = algorithm.getFEs();

            if (num_prints < this.fes_prints.size() &&
                    algorithm_fes >= Integer.parseInt((String) this.fes_prints.get(fes_index))) {
                super.getLog().println(
                        best);
                this.fes_index++;
                num_prints++;
            }

        }


        if (algorithm.getState() == EvolutionaryAlgorithm.FINAL_STATE) {

            while (num_prints < this.fes_prints.size()) {

                super.getLog().println(
                        best);
                num_prints++;
            }

        }


    }

    @Override
    public String getLogID() {
        return "bestfes";
    }
}
