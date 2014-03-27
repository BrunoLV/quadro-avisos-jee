<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<f:view>
	<html>
<head>
</head>
<body>
	<h:form id="loginForm">
		<rich:panel id="loginPanel">
			<f:facet name="header">
				<h:outputLabel value="#{msgs['header.panel.login']}" />
			</f:facet>
			<rich:messages showDetail="true" showSummary="false">
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
			<h:panelGrid columns="3" id="loginPanelGrid">			
				<h:outputLabel value="#{msgs['label.matricula']}"
					id="matriculaOutputLabel" for="matriculaInputText" />
				<h:inputText value="#{LoginController.matricula}"
					id="matriculaInputText" required="true"
					requiredMessage="#{errs['error.requirido']}" />
				<rich:message ajaxRendered="true" for="matriculaInputText">
				<f:facet name="errorMarker">
					<h:graphicImage url="/images/error.png" />
				</f:facet>
				</rich:message>
				<h:outputLabel value="#{msgs['label.senha']}" id="senhaOutputLabel"
					for="senhaInputSecret" />
				<h:inputSecret value="#{LoginController.senha}"
					id="senhaInputSecret" required="true"
					requiredMessage="#{errs['error.requirido']}" />
				<rich:message ajaxRendered="true" for="senhaInputSecret">
				<f:facet name="errorMarker">
					<h:graphicImage url="/images/error.png" />
				</f:facet>
				</rich:message>
			</h:panelGrid>
			<a4j:commandButton value="#{msgs['botao.login']}"
				action="#{LoginController.executarLogin}" />
			<br />
			<br />
			<h:commandLink immediate="true" action="registrar"
				value="#{msgs['label.registro']}" />
		</rich:panel>
	</h:form>
</body>
	</html>
</f:view>