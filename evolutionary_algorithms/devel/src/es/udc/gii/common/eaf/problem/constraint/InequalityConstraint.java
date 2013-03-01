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
 * Constraint.java
 *
 * Created on 18 de diciembre de 2006, 11:04
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.udc.gii.common.eaf.problem.constraint;

/**
 * An abstract class which represents the inequalities constraints.
 * 
 * @author Grupo Integrado de Ingeniería (<a href="http://www.gii.udc.es">www.gii.udc.es</a>)
 * @since 1.0
 */
public abstract class InequalityConstraint extends Constraint {

    @Override
    public boolean isViolated(double value) {
        if (value > this.getConstraintValue())
            return true;
        return false;
    }
    
    
    
}
