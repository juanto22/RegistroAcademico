function handleSubmit(xhr, status, args, dialog) {

	var pfDialog = PF(dialog);
	if (hasFailed(xhr, args)) {
		var jqDialog = jQuery('#' + pfDialog.id);
		if (jqDialog !== undefined && jqDialog !== null) {
			if (!pfDialog.isVisible()) {
				pfDialog.show();
			}

			jqDialog.effect('shake', {
				times : 5
			}, 500);

		}
	} else {
		if (pfDialog !== undefined && pfDialog !== null && pfDialog.isVisible()) {
			pfDialog.hide();
		}
	}
}

function hasFailed(xhr, args) {
	var containsError = xhr.responseText.indexOf('summary:"ERROR"') !== -1
			|| xhr.responseText.indexOf("severity:'error'") !== -1
			|| xhr.responseText.indexOf("severity:'ERROR") !== -1
			|| xhr.responseText.indexOf('severity:"error') !== -1
			|| xhr.responseText.indexOf('severity:"ERROR') !== -1;
	var containsWarning = xhr.responseText.indexOf("severity:'warn'") !== -1;
	return (args.validationFailed || containsError || containsWarning);
}