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

package es.udc.gii.common.eaf.benchmark.constrained_real_param.g17;

import es.udc.gii.common.eaf.problem.constraint.EqualityConstraint;
import java.util.List;

/**
 *
 * @author Grupo Integrado de Ingeniería (<a href="http://www.gii.udc.es">www.gii.udc.es</a>) 
 * @since 1.0
 */
public class G17ConstraintFunction_h4 extends EqualityConstraint {
    
    /** Creates a new instance of G01ConstraintFunction_1 */
    public G17ConstraintFunction_h4() {
    }
    
    public double evaluate(double[] values) {
        
        double constraintValue = Double.MAX_VALUE;
        double x1, x2, x3, x4, x5, x6, aux4;
        double[] norm_values;

        norm_values = G17Function.normalize(values);

        x1 = norm_values[0];
        x2 = norm_values[1];
        x3 = norm_values[2];
        x4 = norm_values[3];
        x5 = norm_values[4];
        x6 = norm_values[5];
        
        aux4 = 200.0 - x3 * x4 * Math.sin(1.48477 - x6) / 131.078 +
               0.90798 * Math.pow(x3, 2.0) * Math.sin(1.47588) / 131.078;
        
        constraintValue = aux4;
        
        return constraintValue;
    }
    
    
}
