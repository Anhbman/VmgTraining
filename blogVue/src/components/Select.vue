<template>
  <div>
    <el-select
    v-model="selected.id"
    placeholder="Category"
    @change="clickSelect"
    >
    <el-option v-for="item in options"
      :key="item.id"
      :label="item.name"
      :value="item.id"
      ></el-option>
    </el-select>
  </div>
</template>

<script>
export default {
  data() {
    return {
    }
  },
  props: {
    options: Array,
    selected: Object,
  },
  methods: {
    clickSelect(val) {
      this.selected.id = val;
      let tmp = this.options.filter(value =>
      value.id == val);
      this.selected.id = tmp[0].id;
      this.selected.name = tmp[0].name;
      // console.log(this.selected);
      this.$emit('ClickSelect', this.selected);
    }
  },
  mounted() {
    // this.selectedValue = this.selected;
  }
}
</script>