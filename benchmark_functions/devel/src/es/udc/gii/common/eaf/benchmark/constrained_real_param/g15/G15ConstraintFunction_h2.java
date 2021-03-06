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
 * G01ConstraintFunction_1.java
 *
 * Created on 27 de agosto de 2007, 19:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.udc.gii.common.eaf.benchmark.constrained_real_param.g15;

import es.udc.gii.common.eaf.problem.constraint.EqualityConstraint;

/**
 *
 * @author Grupo Integrado de Ingeniería (<a href="http://www.gii.udc.es">www.gii.udc.es</a>) 
 * @since 1.0
 */
public class G15ConstraintFunction_h2 extends EqualityConstraint {
    
    public G15ConstraintFunction_h2() {
    }

    @Override
    public double evaluate(double[] values) {

        double[] norm_values;

        norm_values = G15Function.normalize(values);
        
        return 8.0 * norm_values[0] + 14.0 * norm_values[1] + 7.0 * norm_values[2] - 56.0;

    }

    
}
