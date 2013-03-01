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


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.udc.gii.common.eaf.algorithm.operator.reproduction.mutation.de.mutationStrategy;

import java.util.List;
import org.apache.commons.configuration.Configuration;
import es.udc.gii.common.eaf.algorithm.EvolutionaryAlgorithm;
import es.udc.gii.common.eaf.algorithm.population.Individual;
import es.udc.gii.common.eaf.plugin.individual.ClosestIndividual;
import es.udc.gii.common.eaf.plugin.individual.IndividualChooser;
import es.udc.gii.common.eaf.util.EAFRandom;
import java.util.ArrayList;

/**
 * The mutation operator of the Differential Evolution Algorithm use different mutation strategies to
 * create the individuals of the population. This class implements the random strategy.
 * Following this strategy a new mutation vector is generated as follows: <p>
 *
 * v<sub>i,g</sub> = x<sub>r1,g</sub> + F&sdot;(x<sub>r2,g</sub> - x<sub>r3,g</sub>) <p>
 *
 * where r1, r2 and r3 are distinct integers uniformely chosen from from the set <i>{1, 2, &hellip;, NP}</i>
 * and F is the mutation factor which is implemented as a Plugin. In the canonical version F is a constant
 * double value.
 *
 * To configure this mutation strategy the xml code should be:<p>
 *
 * <pre>
 * {@code
 * <MutationStrategy>
 *      <Class>es.udc.gii.common.eaf.algorithm.operator.reproduction.mutation.de.mutationStrategy.RandomDEMutationStrategy</Class>
 *      <F>value</F><br>
 *      <diffVector>value</diffVector><br>
 * <MutationStrategy><p>
 * }
 * </pre>
 *
 * where  F and diffVector are parameters inherit from the DEMutationStrategy class. If
 * some of the parameters do not appear in the configuration, they are set to their default values.<p>
 *
 * Default values:
 * <ul>
 * <li>F as a constant parameter with value 0.5</li>
 * <li>diffVector = 1 </li>
 * </ul>
 *
 *
 * @author Grupo Integrado de Ingeniería (<a href="http://www.gii.udc.es">www.gii.udc.es</a>)
 * @since 1.0
 */
public class NRandDEMutationStrategy extends DEMutationStrategy {

    @Override
    public void configure(Configuration conf) {
        super.configure(conf);
    }

    @Override
    public Individual getMutatedIndividual(EvolutionaryAlgorithm algorithm, Individual target) {

        int basePos;
        Individual base;
        List<Individual> individuals;
        List<Individual> listInd;
        List<Integer> index_list;
        int randomPos;
        double[] chromosome;
        double auxGeneValue, x1, x2;
        double F;
        IndividualChooser chooser = new ClosestIndividual();

        F = this.getFPlugin().get(algorithm);
        individuals = algorithm.getPopulation().getIndividuals();

        //Select the "base" individual based on the chooser, in this case ClosestIndividual:
        base = (Individual) chooser.get(algorithm, individuals, target).clone();
        basePos = individuals.indexOf(base);
        
        
        index_list = new ArrayList<Integer>();
        index_list.add(basePos);

        //se eligen los vectores diferenciales:
        listInd = new ArrayList<Individual>();

        for (int i = 0; i < this.getDiffVector() * 2; i++) {

            do {

                randomPos = (int) EAFRandom.nextInt(individuals.size());

            } while (index_list.contains(randomPos));

            index_list.add(randomPos);
            listInd.add(individuals.get(randomPos));

        }

        if (base != null) {
            //Recorremos el numero de genes:
            chromosome = base.getChromosomeAt(0);
            for (int i = 0; i < base.getChromosomeAt(0).length; i++) {

                auxGeneValue = chromosome[i];

                for (int j = 0; j < this.getDiffVector(); j += 2) {

                    x1 = listInd.get(j).getChromosomeAt(0)[i];
                    x2 = listInd.get(j + 1).getChromosomeAt(0)[i];


                    auxGeneValue += F * (x1 - x2);

                }

                chromosome[i] = auxGeneValue;

            }
            base.setChromosomeAt(0, chromosome);
        }

        return base;

    }
}
