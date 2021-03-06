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
 * Wfg8_Objective.java
 *
 * Created on November 21, 2007, 3:05 PM
 *
 */
package es.udc.gii.common.eaf.benchmark.multiobjective.wfg.wfg8;

import es.udc.gii.common.eaf.benchmark.multiobjective.wfg.Wfg_Objective;

/**
 * CEC 2007 Testsuite: WFG8.
 * @author Grupo Integrado de Ingeniería (<a href="http://www.gii.udc.es">www.gii.udc.es</a>) 
 * @since 1.0
 */
public class Wfg8_Objective extends Wfg_Objective {

    /** Creates a new instance of Wfg8_Objective */
    public Wfg8_Objective() {
    }

    @Override
    public double evaluate(double[] z) {
        int nx = z.length;

        int i, j;
        double[] y = new double[30];
        double[] t1 = new double[30];
        double[] t2 = new double[30];
        double[] t3 = new double[5];

        final int k = (numberOfObjectives == 2) ? 4 : 2 * (numberOfObjectives - 1);

        for (i = 0; i < nx; i++) {
            y[i] = ((i + 1) * z[i] + (i + 1)) / (2.0 * (i + 1));
        }

        //y=WFG8_t1( y, k);
        for (i = 0; i < k; i++) {
            t1[i] = y[i];
        }

        for (i = k; i < nx; i++) {
            final int head = 0;
            final int tail = i;

            double[] y_sub = new double[30];
            double[] w_sub = new double[30];

            for (j = head; j < tail; j++) {
                y_sub[j - head] = y[j];
                w_sub[j - head] = 1;
            }
            {
                final double u = r_sum(y_sub, w_sub, tail - head);
                t1[i] = b_param(y[i], u, 0.98 / 49.98, 0.02, 50);
            }

        }

        //y=WFG1_t1( y, k);
        for (i = 0; i < k; i++) {
            t2[i] = t1[i];
        }
        for (i = k; i < nx; i++) {
            t2[i] = s_linear(t1[i], 0.35);
        }

        //y=WFG2_t3( y, k, numberOfObjectives);
        {
            double[] y_sub = new double[30];
            double[] w_sub = new double[30];
            double[] y_sub2 = new double[30];
            double[] w_sub2 = new double[30];

            for (i = 1; i <= numberOfObjectives - 1; i++) {
                final int head = (i - 1) * k / (numberOfObjectives - 1);
                final int tail = i * k / (numberOfObjectives - 1);

                for (j = head; j < tail; j++) {
                    y_sub[j - head] = t2[j];
                    w_sub[j - head] = 1;
                }

                t3[i - 1] = r_sum(y_sub, w_sub, tail - head);
            }

            for (j = k; j < nx; j++) {
                y_sub2[j - k] = t2[j];
                w_sub2[j - k] = 1;
            }

            t3[numberOfObjectives - 1] = r_sum(y_sub2, w_sub2, nx - k);
        }

        //f_result=WFG4_shape(y);
        {
            int m;
            int[] A = new int[5];
            double[] x = new double[5];
            double[] h = new double[5];
            double[] S = new double[5];

            A[0] = 1;

            for (i = 1; i < numberOfObjectives - 1; i++) {
                A[i] = 1;
            }

            for (i = 0; i < numberOfObjectives - 1; i++) {
                double tmp1;
                tmp1 = t3[numberOfObjectives - 1];
                if (A[i] > tmp1) {
                    tmp1 = A[i];
                }
                x[i] = tmp1 * (t3[i] - 0.5) + 0.5;
            }

            x[numberOfObjectives - 1] = t3[numberOfObjectives - 1];

            for (m = 1; m <= numberOfObjectives; m++) {
                h[m - 1] = concave(x, m, numberOfObjectives);
            }

            for (m = 1; m <= numberOfObjectives; m++) {
                S[m - 1] = m * 2.0;
            }

            return 1.0 * x[numberOfObjectives - 1] + S[objNumber - 1] * h[objNumber - 1];
        }
    }

    @Override
    public void reset() {
    }

    @Override
    public String toString() {
        return "Wfg8_Objective";
    }
}
