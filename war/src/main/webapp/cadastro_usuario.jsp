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
	<h:form id="usuarioForm">
		<rich:panel id="usuarioPanel">
			<f:facet name="header">
				<h:outputLabel value="#{msgs['label.cadastro.usuario']}" />
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
			<h:inputHidden value="#{UsuarioController.usuario.id}" />
			<h:panelGrid columns="3" id="usuarioPanelGrid">
				<h:outputLabel value="#{msgs['label.usuario.nome']}" id="nomeOutputLabel" for="nomeInputText" />
				<h:inputText value="#{UsuarioController.usuario.nome}" id="nomeInputText" required="true" requiredMessage="#{errs['error.requirido']}" />
				<rich:message ajaxRendered="true" for="nomeInputText">
				<f:facet name="errorMarker">
					<h:graphicImage url="/images/error.png" />
				</f:facet>
				</rich:message>
				<h:outputLabel value="#{msgs['label.usuario.matricula']}" id="matriculaOutputLabel" />	
				<h:inputText value="#{UsuarioController.usuario.matricula}" id="matriculaInputText" required="true" requiredMessage="#{errs['error.requirido']}" />
				<rich:message ajaxRendered="true" for="matriculaInputText">
				<f:facet name="errorMarker">
					<h:graphicImage url="/images/error.png" />
				</f:facet>
				</rich:message>
				<h:outputLabel value="#{msgs['label.usuario.senha']}" id="senhaOutputLabel" />
				<h:inputSecret value="#{UsuarioController.usuario.senha}" id="senhaInputSecret" required="true" requiredMessage="#{errs['error.requirido']}" />
				<rich:message ajaxRendered="true" for="senhaInputSecret">
				<f:facet name="errorMarker">
					<h:graphicImage url="/images/error.png" />
				</f:facet>
				</rich:message>
			</h:panelGrid>
			<a4j:commandButton value="#{msgs['botao.salvar']}"
				action="#{UsuarioController.cadastrarUsuario}"
				reRender="usuarioForm" />
				<h:commandButton value="#{msgs['botao.cancelar']}" action="cancelar"  immediate="true" />
		</rich:panel>
	</h:form>
</body>
	</html>
</f:view>