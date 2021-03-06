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
 * BestMigration.java
 *
 * Created on April 1, 2008, 2:40 PM
 *
 */
package es.udc.gii.common.eaf.algorithm.parallel.migration.selection;

import es.udc.gii.common.eaf.algorithm.EvolutionaryAlgorithm;
import es.udc.gii.common.eaf.algorithm.population.Individual;
import es.udc.gii.common.eaf.algorithm.productTrader.IndividualsProductTrader;
import es.udc.gii.common.eaf.algorithm.productTrader.specification.BestIndividualSpecification;
import es.udc.gii.common.eaf.util.ConfWarning;
import java.util.List;
import org.apache.commons.configuration.Configuration;

/**
 * A selection strategy for selecting the best individuals of the current population
 * for migrating to other islands.
 *
 * @author Grupo Integrado de Ingeniería (<a href="http://www.gii.udc.es">www.gii.udc.es</a>)
 * @since 1.0
 */
public class BestMigration implements MigSelectionStrategy {

    private int howMany = 0;

    /** Creates a new instance of BestMigration */
    public BestMigration() {
        this.howMany = 2;
        (new ConfWarning("BestMigration.HowMany", 2)).warn();
    }

    /** Creates a new instance of BestMigration */
    public BestMigration(int howMany) {
        setHowMany(howMany);
    }

    @Override
    public List<Individual> getIndividualsForMigration(EvolutionaryAlgorithm algorithm,
            List<Individual> individuals) {
        return IndividualsProductTrader.get(new BestIndividualSpecification(),
                individuals, getHowMany(), algorithm.getComparator());
    }

    @Override
    public void configure(Configuration conf) {
        setHowMany(conf.getInt("HowMany"));
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }
}
