<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.io.*,java.util.*" %>

<%!
    public void jspInit(){

        System.out.println("######## INIT METODU CALISTI ########");

    }

    public void jspDestroy(){

        System.out.println("######## DESTROY METODU CALISTI ########");

    }
%>

<c:set var="isHighAccess" value="${operator.type == 'ADMIN'}"/>
<c:if test="${not empty message}">
    <c:choose>
        <c:when test="${message == '0'}">
            <div class="alert alert-success alert-dismissable">
                <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
                İşleminiz Başarıyla Gerçekleştirildi.
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger alert-dismissable">
                <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
                İşleminiz Sırasında Hata Oluştu.<br>(${message})
            </div>
        </c:otherwise>
    </c:choose>
</c:if>


<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins col-lg-12">
            <div class="ibox-content">
                <div class="file-manager">
                    <div>
                        <div>


                            <%--
                                // Set refresh, autoload time as 5 seconds
                                response.setIntHeader("Refresh", 30);

                                // Get current time
                                Calendar calendar = new GregorianCalendar();

                                String am_pm;
                                int hour = calendar.get(Calendar.HOUR);
                                int minute = calendar.get(Calendar.MINUTE);
                                int second = calendar.get(Calendar.SECOND);

                                if(calendar.get(Calendar.AM_PM) == 0)
                                    am_pm = "AM";
                                else
                                    am_pm = "PM";
                                String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
                                out.println("Current Time is: " + CT + "\n");
                            --%>

                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs" role="tablist" id="metaTabs">
                                <li role="presentation">
                                    <a href="#product" aria-controls="product" role="tab" data-toggle="tab">Ürün</a>
                                </li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="bullet">
                                    <h3>File Upload:</h3>
                                    Select a file to upload: <br />
                                    <form action = "/upload" method = "post"
                                          enctype = "multipart/form-data">
                                        <input type = "file" name = "csvFile" size = "50" />
                                        <br />
                                        <input type = "submit" value = "Upload File" />
                                    </form>
                                </div>

                            </div>

                        </div>

                        <div class="hr-line-dashed"></div>
                        <div>
                            <div class="alert alert-danger">
                                <strong>Tab ile ayrılmış</strong> CSV veya TSV yüklemelisiniz.
                            </div>
                            <h5>
                                Sitedeki birden fazla ürünün, markanın veya marka / kategorinin meta title ve description bilgilerini tek seferde bu
                                ekranda güncelleyebilirsiniz.
                            </h5>
                            Adımlar;
                            <br/>
                            <ol style="margin-top: 5px;">
                                <li>
                                    Sistem bir dosyada en fazla 1000 adet satır kabul etmektedir. Daha fazla satırı
                                    güncellemek istiyorsanız, farklı dosyalara ayırın.
                                </li>
                                <li>
                                    Excel içerisinden “CSV Export” seçeneği ile "TAB" ile ayrılmış CSV dosyası çıktısı alın.
                                </li>
                                <li>
                                    CSV dosyanızı aşağıdaki dosya yükleme butonu ile sisteme yükleyin. “CSV Yükle” Butonuna tıklayın.
                                </li>
                                <li>
                                    Eğer dosya içerisinde eksik veya hatalı bilgi girişi var ise HATA mesajı
                                    alırsınız. Dosyanızı tekrar kontrol edin ve sisteme yüklemeyi deneyin.
                                </li>
                                <li>
                                    BAŞARILI mesajını aldığınızda yeni meta bilgileri sitede güncellenmiş olacaktır.
                                </li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- CSS EKLENTILERI -->
<style>
    li {
        margin: 3px 0;
    }
    .tab-content .tab-pane {
        padding: 40px 0;
    }
</style>

<script type="application/javascript">
    $(document).ready(function () {
        $('#metaTabs a').click(function (e) {
            e.preventDefault()
            $(this).tab('show')
        })

        $('#uploadCSVProduct, #uploadCSVBrand, #uploadCSVBrandCategory').click('click', function (e) {
            var form = $(this).closest("form");
            bootbox.confirm("Bu isleme devam etmek istediğinizden emin misiniz? ", function (result) {
                if (result) {
                    form.submit();
                }
            });
        });
    });
</script>
