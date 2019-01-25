<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/taglib.jsp" %>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<c:if test="${not empty message}">
    <c:choose>
        <c:when test="${message == 'no error'}">
            <div class="alert alert-success alert-dismissable">
                <button aria-hidden="true" data-dismiss="alert" class="close"
                        type="button">×
                </button>
                İşleminiz Başarıyla Gerçekleştirildi.
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger alert-dismissable">
                <button aria-hidden="true" data-dismiss="alert" class="close"
                        type="button">×
                </button>
                İşleminiz Sırasında Hata Oluştu.<br>(${message})
            </div>
        </c:otherwise>
    </c:choose>
</c:if>

<input type="hidden" id="redirectComponentType"
       value="${component.componentType}">
<input type="hidden" id="redirectCategoryId"
       value="${component.categoryId}">

<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>Anasayfa Yönetimi</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">

                <div class="ibox-content m-b-sm border-bottom">
                    <div class="panel-options">
                        <div class="btn-group btn-group-justified" role="group">

                            <div class="col-lg-3">
                                <div class="btn-group">
                                    <select autofocus class="form-control"
                                            name="component" id="pages"
                                            style="width: 255px;">
                                        <option hidden selected disabled
                                                value="default">Komponent
                                            Seçiniz
                                        </option>
                                        <option value="header">Header Banner
                                        </option>
                                        <option value="teaser">Teaser</option>
                                        <option value="collective">
                                            Collective-1
                                        </option>
                                        <option value="collective2">Collective-2
                                        </option>
                                        <option value="collective4">Collective-3
                                        </option>
                                        <option value="collective3">Collective-4
                                        </option>
                                        <option value="collective5">Collective-5
                                        </option>
                                        <option value="stand">Stand</option>
                                        <option value="sandwich">Sandwich
                                        </option>
                                        <option value="sandwich_master">Sandwich
                                            Master
                                        </option>
                                        <option value="category">Megamenü
                                        </option>
                                        <option value="store">Store</option>
                                        <option value="brand">Brand</option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-lg-3">
                                <div class="btn-group">
                                    <select autofocus
                                            class="form-control nodisplay"
                                            name="component" id="categories"
                                            style="width: 255px;">
                                        <option hidden selected disabled
                                                value="default">Kategori Seçiniz
                                        </option>
                                        <option value="80">Elektronik</option>
                                        <option value="5173">Ev</option>
                                        <option value="153">Anne Bebek</option>
                                        <option value="404">Saat</option>
                                        <option value="5170">Kitap</option>
                                        <option value="290">Spor</option>
                                        <option value="1308">Sağlık</option>
                                        <option value="2333">Oto</option>

                                        <option value="5174">Süpermarket
                                        </option>
                                    </select>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div hidden class="componentContainer"></div>

            </div>
        </div>
    </div>
</div>

<%--JS EKLENTİLERİ--%>
<script src="<c:url value='/js/plugins/jstree/jstree.min.js'/>"></script>
<script src="<c:url value='/js/plugins/bootbox/bootbox.js'/>"></script>

<script src="<c:url value='/js/bo/service/boservice.js'/>"></script>
<script src="<c:url value='/js/bo/homepagemanager.js?irev=${bots}'/>"></script>

