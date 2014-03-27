package br.com.porto.amazonas.quadro.service.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

/**
 * Interceptador de EJB's de negócio.
 * Esse interceptador é utilizado para realizar log da ação de negócio.
 * @author Bruno Luiz Viana
 */
public class ServiceInterceptor {
	
	@AroundInvoke
	public Object logarAcaoDeNegocio(InvocationContext context) throws Exception {
		Logger logger = Logger.getLogger(context.getTarget().getClass().getName());
		logger.info(String.format("Executando método ==> %s", context.getMethod().getName()));
		Object[] parametros = context.getParameters();
		for (Object parametro : parametros) {
			logger.info("Parametro: " + parametro);
		} // fim do bloco for
		Object retornoExecucao = context.proceed();
		logger.info(String.format("Retorno da execução: %s", retornoExecucao));
		return retornoExecucao; 
	} // fim do método logarAcaoDeNegocio

} // fim da classe ServiceInterceptor