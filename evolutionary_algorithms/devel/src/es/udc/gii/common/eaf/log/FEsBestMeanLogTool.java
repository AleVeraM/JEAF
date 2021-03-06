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
import es.udc.gii.common.eaf.algorithm.fitness.FitnessUtil;
import es.udc.gii.common.eaf.algorithm.population.Individual;
import es.udc.gii.common.eaf.algorithm.productTrader.IndividualsProductTrader;
import es.udc.gii.common.eaf.algorithm.productTrader.specification.BestIndividualSpecification;
import java.util.Map;
import java.util.Observable;
import org.apache.commons.configuration.Configuration;

/**
 * This log tool implements a log tool that record the number of function evaluations performed, the
 * fitness of the best individual and the mean fitness of the whole population after the replace stage.
 * This log tool does not need any kind of configuration, apart from the configuration need in the superclass.
 *
 * @author Grupo Integrado de Ingeniería (<a href="http://www.gii.udc.es">www.gii.udc.es</a>)
 * @since 1.0
 */
public class FEsBestMeanLogTool extends LogTool {
    
    /** Creates a new instance of BestMeanLogTool */
    public FEsBestMeanLogTool() {}
    
    public void setParameters(Map<String,String> parameters) {
    }

    @Override
    public void configure(Configuration conf) {
        try {
            super.configure(conf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        
        EvolutionaryAlgorithm algorithm = (EvolutionaryAlgorithm)o;
        BestIndividualSpecification bestSpec =
                new BestIndividualSpecification();
        Individual best;
        
        super.update(o, arg);
        
        if (algorithm.getState() == EvolutionaryAlgorithm.REPLACE_STATE
                && arg == null) {
            best = (Individual) IndividualsProductTrader.get(bestSpec,
                    algorithm.getPopulation().getIndividuals(),1,algorithm.getComparator()).get(0);
            
            super.getLog().println(
                    algorithm.getFEs() + "\t" +
                    best.getFitness() + "\t" +
                    FitnessUtil.meanFitnessValue(
                    algorithm.getPopulation().getIndividuals()));
        }
    }

    @Override
    public String getLogID() {
        return "bestfesmean";
    }
    
    
    
}
