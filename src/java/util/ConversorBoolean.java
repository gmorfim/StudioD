
import javax.persistence.AttributeConverter;

/** 
 * Converte um atributo da entidade booleano para um único caractere 
 * cadeia Y / N que será armazenado no banco de dados, e vice-versa 
 * 
 * @author jtough 
 * /
package util;

import javax.persistence.AttributeConverter;

/**
 *
 * @author Gustavo
 */
public class ConversorBoolean implements AttributeConverter<Boolean, String> {    
         
    /**     
     * Esta implementação irá retornar "Y" se o parâmetro for Boolean.TRUE, 
     * caso contrário ele irá retornar "F" quando o parâmetro é Boolean.FALSE. 
     * um valor de entrada nulo irá produzir um valor de retorno null. 
     * @ Param b 
     */
    @Override
    public String convertToDatabaseColumn(Boolean b) {
        if (b == null) {
            return null;
        }
        if (b.booleanValue()) {
            return "Y";
        }
        return "F";
    }

    /**
     Esta implementação irá retornar Boolean.TRUE se a string 
     * é "Y" ou "y", caso contrário ele irá ignorar o valor e retornar 
     * Boolean.FALSE (que na verdade não procure por "F") para qualquer 
     * outro não nula corda. Um valor de entrada nulo irá produzir um nulo 
     valor * return. 
     * @param S
     */
    @Override
    public Boolean convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        if (s.equals("Y") || s.equals("y")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
    

