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
	<h:form id="avisosForm">
		<rich:panel id="listaPanel">
			<f:facet name="header">
				<h:outputLabel value="#{msgs['header.table']}" />
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
			<h:commandButton action="novo" value="#{msgs['botao.novo']}" />
			<br />
			<br />
			<rich:dataTable id="avisoDataTable" value="#{AvisoController.avisos}"
				var="avisoSelecionado" rows="5">
				<f:facet name="header">
					<rich:columnGroup>
						<rich:column>
							<h:outputText value="#{msgs['header.table.id']}" />
						</rich:column>
						<rich:column>
							<h:outputText value="#{msgs['header.table.texto']}" />
						</rich:column>
						<rich:column>
							<h:outputText value="#{msgs['header.table.nome_usuario']}" />
						</rich:column>
						<rich:column>
							<h:outputText value="#{msgs['header.table.cadastro']}" />
						</rich:column>
						<rich:column>
							<h:outputText value="#{msgs['header.table.vigencia']}" />
						</rich:column>
					</rich:columnGroup>
				</f:facet>
				<rich:column>
					<h:outputText value="#{avisoSelecionado.id}" />
				</rich:column>
				<rich:column>
					<h:outputText value="#{avisoSelecionado.texto}" />
				</rich:column>
				<rich:column>
					<h:outputText value="#{avisoSelecionado.usuario.nome}" />
				</rich:column>
				<rich:column>
					<h:outputText value="#{avisoSelecionado.dataCadastro}">
						<f:convertDateTime pattern="dd/MM/yyyy" />
					</h:outputText>
				</rich:column>
				<rich:column>
					<h:outputText value="#{avisoSelecionado.vigencia}">
						<f:convertDateTime pattern="dd/MM/yyyy" />
					</h:outputText>
				</rich:column>
				<rich:column>
					<h:commandButton value="#{msgs['botao.editar']}"
						action="#{AvisoController.selecionarParaEdicao}" rendered="#{avisoSelecionado.usuario.id.equals(sessionScope.usuarioLogado.id)}">
						<f:setPropertyActionListener target="#{AvisoController.aviso}"
							value="#{avisoSelecionado}" />
					</h:commandButton>
				<h:outputText value="|" rendered="#{avisoSelecionado.usuario.id.equals(sessionScope.usuarioLogado.id)}" />
				<a4j:commandButton value="#{msgs['botao.remover']}"
						action="#{AvisoController.removerAviso}" reRender="avisosForm"
						immediate="true" rendered="#{avisoSelecionado.usuario.id.equals(sessionScope.usuarioLogado.id)}">
						<f:setPropertyActionListener target="#{AvisoController.aviso}"
							value="#{avisoSelecionado}" />
					</a4j:commandButton>
				</rich:column>
				<f:facet name="footer">
					<rich:datascroller pageIndexVar="indexPagina" pagesVar="paginas">
						<h:outputText value="#{indexPagina} / #{paginas} }" />
					</rich:datascroller>
				</f:facet>
			</rich:dataTable>
		</rich:panel>
	</h:form>
</body>
	</html>
</f:view>