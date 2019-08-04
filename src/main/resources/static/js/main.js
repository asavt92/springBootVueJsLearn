function getIndex(list, id) {

    for (let i = 0; i < list.length; i++) {
        if (list[i].id == id) {
            return i;
        }
    }
    return -1;
}

var messageApi = Vue.resource("/message{/id}");

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        messageAttr: function (nVal, oVal) {
            this.text = nVal.text;
            this.id = nVal.id;
        }
    },
    template: '<div >' +
        '<input placeholder="Input text..." type="text" v-model="text"></input>' +
        '<input type="button" value="save" @click="save"></input>' +
        '</div>',
    methods: {
        save: function () {
            var mes = {"text": this.text};

            if (this.id) {
                messageApi.update({id: this.id}, mes).then(result => result.json().then(data => {
                    index = getIndex(this.messages, data.id);
                    this.messages.splice(index, 1, data);
                    this.text = '';
                    this.id = '';
                }));
            } else {
                messageApi.save({}, mes).then(result => result.json().then(
                    data => {
                        this.messages.push(data);
                        this.text = '';
                    }
                ));
            }
        }
    }
});


Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template: '<div > <i>{{ message.id }}</i> {{ message.text }}' +
        '<span style="position: absolute; right: 0px;"> ' +
        '<input type="button" value="Edit" @click="edit">' +
        '<input type="button" value="X" @click="del"> </span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        },
        del: function () {
            messageApi.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1);
                }
            })

        }
    }
});

Vue.component('messages-list', {
    data: function () {
        return {message: null}
    },
    props: ["messages"],
    template: '<div style="position: relative; width: 300px">' +
        '<message-form :messages="messages" :messageAttr="message"> </message-form>' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" :editMethod="editMethod" :messages="messages"></message-row></div>',
    created: function () {
        messageApi.get().then(result => result.json().then(
            data => data.forEach(mes => this.messages.push(mes)
            )
        ))
        ;
    },
    methods: {
        editMethod: function (mes) {
            this.message = mes;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: "<messages-list :messages='messages' />",
    data: {
        messages: []
    }
});
