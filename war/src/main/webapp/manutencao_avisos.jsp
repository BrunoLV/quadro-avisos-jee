<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<f:view>
	<html>
<head></head>
<body>
	<c:import url="logout.jsp" />
	<h:form id="avisoForm">
		<rich:panel id="avisoPanel">
			<f:facet name="header">
				<h:outputLabel value="#{msgs['header.panel.cad_aviso']}" />
			</f:facet>
			<rich:messages showDetail="true" showSummary="false"
				globalOnly="true">
				<f:facet name="errorMarker">
					<h:graphicImage url="/images/error.png" />
				</f:facet>
				<f:facet name="infoMarker">
					<h:graphicImage url="/images/Info.png" />
				</f:facet>
				<f:facet name="fatalMarker">
					<h:graphicImage url="/images/fatal.png" />
				</f:facet>
				<f:facet name="warnMarker">
					<h:graphicImage url="/images/warning.png" />
				</f:facet>
			</rich:messages>
			<h:inputHidden value="#{AvisoController.aviso.id}" />
			<h:panelGrid columns="3" id="avisoPanelGrid">
				<h:outputLabel value="#{msgs['label.aviso']}" id="avisoOutputLabel" for="avisoInputTextArea" />
				<h:inputTextarea value="#{AvisoController.aviso.texto}"
					id="avisoInputTextArea" required="true" rows="6" cols="30"
					requiredMessage="#{errs['error.requirido']}" />
				<rich:message ajaxRendered="true" for="avisoInputTextArea" >
				<f:facet name="errorMarker">
					<h:graphicImage url="/images/error.png" />
				</f:facet>
				</rich:message>
				<h:outputLabel value="#{msgs['label.vigencia']}"
					id="vigenciaOutputLabel" for="vigenciaCalendar" />
				<rich:calendar value="#{AvisoController.aviso.vigencia}"
					id="vigenciaCalendar" required="true"
					requiredMessage="#{errs['error.requirido']}"
					datePattern="dd/MM/yyyy" />
				<rich:message ajaxRendered="true" for="vigenciaCalendar">
					<f:facet name="errorMarker">
						<h:graphicImage url="/images/error.png" />
					</f:facet>
				</rich:message>
			</h:panelGrid>
			<a4j:commandButton value="#{msgs['botao.salvar']}" action="#{AvisoController.cadastrarAviso}" reRender="avisoForm"  />
			<h:commandButton value="#{msgs['botao.cancelar']}" action="#{AvisoController.cancelarManutencaoAviso}" immediate="true"  />
		</rich:panel>
	</h:form>
</body>
	</html>
</f:view>
